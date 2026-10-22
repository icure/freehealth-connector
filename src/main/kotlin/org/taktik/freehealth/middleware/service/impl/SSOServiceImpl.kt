package org.taktik.freehealth.middleware.service.impl

import com.nimbusds.jose.JOSEObjectType
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.jwk.Curve
import com.nimbusds.jose.crypto.ECDSASigner
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.crypto.impl.ECDSA
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import com.nimbusds.oauth2.sdk.ClientCredentialsGrant
import com.nimbusds.oauth2.sdk.TokenRequest
import com.nimbusds.oauth2.sdk.TokenResponse
import com.nimbusds.oauth2.sdk.auth.JWTAuthenticationClaimsSet
import com.nimbusds.oauth2.sdk.auth.PrivateKeyJWT
import com.nimbusds.oauth2.sdk.http.HTTPRequest
import com.nimbusds.oauth2.sdk.id.Audience
import com.nimbusds.oauth2.sdk.id.ClientID
import com.nimbusds.oauth2.sdk.token.TokenTypeURI
import com.nimbusds.oauth2.sdk.token.TypelessToken
import com.nimbusds.oauth2.sdk.tokenexchange.TokenExchangeGrant
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.technical.enumeration.SsoProfile
import org.taktik.connector.technical.service.sso.SingleSignOnService
import org.taktik.connector.technical.service.sso.impl.SingleSignOnServiceImpl
import org.taktik.freehealth.middleware.dao.KeystoreProviderService
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import org.taktik.freehealth.middleware.domain.sts.IamTokenResult
import org.taktik.freehealth.middleware.exception.IamTokenExchangeException
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.SSOService
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Element
import java.io.StringWriter
import java.net.URI
import java.security.PrivateKey
import java.security.Provider
import java.security.PublicKey
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPrivateKey
import java.util.*
import java.util.UUID
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


@Service
class SSOServiceImpl(private val stsService: STSService, private val userDetailsService: KeystoreProviderService) : SSOService {
    val ssosi: SingleSignOnService = SingleSignOnServiceImpl()

    override fun getLegacySSOBearerToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, destination: String?, profile: String?): BearerToken? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        return ssosi.signin(profile?.let { SsoProfile.valueOf(it) } ?: SsoProfile.SAML2_POST, samlToken, destination)?.let { BearerToken(it) }
    }


    override fun getM2MOauth2Token(tokenId: UUID, keystoreId: UUID, passPhrase: String, cbe: String, kid: String): TokenResponse {
        val isAcceptance = stsService.isAcceptance()

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User ?: throw AuthenticationCredentialsNotFoundException("JWT tokens are only available through authenticated accesses")
        val orgKeystoreUuid = (if (isAcceptance) principal.orgKeystoreAccUuid else principal.orgKeystoreProdUuid)?.let { UUID.fromString(it) } ?: throw AuthenticationCredentialsNotFoundException("Organisation keystore was not set")
        val orgKeystorePassword = (if (isAcceptance) principal.orgKeystoreAccPassword else principal.orgKeystoreProdPassword) ?: throw AuthenticationCredentialsNotFoundException("Organisation keystore password was not set")

        stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for SSO operations")

        val orgKeystore = try { stsService.getKeyStore(orgKeystoreUuid, orgKeystorePassword) } catch (e: MissingKeystoreException) { null }?:
                            userDetailsService.getKeystore(principal, if (isAcceptance) "org-keystore-acc" else "org-keystore-prod")?.let { keyStore ->
                                stsService.uploadKeystore(keyStore).let {
                                    if (it != orgKeystoreUuid) throw java.lang.IllegalStateException("Mismatch in keystore UUID")
                                    stsService.getKeyStore(it, orgKeystorePassword)
                                }
                            } ?: throw IllegalStateException("Missing org keystore")

        val key = orgKeystore.getKey("authentication", orgKeystorePassword.toCharArray())

        val tokenRequest = TokenRequest(
            URI(if (isAcceptance) "https://api-acpt.ehealth.fgov.be/auth/realms/M2M/protocol/openid-connect/token" else "https://api.ehealth.fgov.be/auth/realms/M2M/protocol/openid-connect/token"),
            PrivateKeyJWT(
                JWTAuthenticationClaimsSet(
                    ClientID("cbe-$cbe"),
                    Audience(if (isAcceptance) "https://api-acpt.ehealth.fgov.be/auth/realms/M2M" else "https://api.ehealth.fgov.be/auth/realms/M2M")
                ),
                JWSAlgorithm.RS256,
                key as? RSAPrivateKey ?: throw AuthenticationCredentialsNotFoundException("Cannot find private key for signature"),
                kid,
                null
            ),
            ClientCredentialsGrant()
        )

        val httpRequest: HTTPRequest = tokenRequest.toHTTPRequest()
        httpRequest.accept = MediaType.APPLICATION_JSON_VALUE
        httpRequest.connectTimeout = 30000
        httpRequest.readTimeout = 30000
        return TokenResponse.parse(httpRequest.send())
    }

    override fun exchangeSamlTokenForIamToken(tokenId: UUID, keystoreId: UUID, passPhrase: String, clientId: String, ssin: String?, refreshable: Boolean): IamTokenResult {
        val isAcceptance = stsService.isAcceptance()
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw MissingTokenException("Cannot obtain SAML token for IAM token exchange")
        samlToken.checkValidity()

        val assertion = samlToken.assertion
        val subject = ssin ?: getCertificateHolderSsin(assertion) ?: throw IllegalArgumentException("No SSIN provided and none found in the SAML token")
        val subjectTokenType = when (assertion.namespaceURI) {
            SAML1_ASSERTION_NS -> TokenTypeURI.SAML1
            SAML2_ASSERTION_NS -> TokenTypeURI.SAML2
            else -> throw IllegalArgumentException("Unsupported SAML assertion namespace ${assertion.namespaceURI}")
        }
        val subjectToken = Base64.getUrlEncoder().withoutPadding().encodeToString(toXmlString(assertion).toByteArray(Charsets.UTF_8))

        val tokenRequest = TokenRequest(
            URI(if (isAcceptance) "https://api-acpt.ehealth.fgov.be/auth/realms/healthcare/protocol/openid-connect/token" else "https://api.ehealth.fgov.be/auth/realms/healthcare/protocol/openid-connect/token"),
            ClientID(clientId),
            TokenExchangeGrant(
                TypelessToken(subjectToken),
                subjectTokenType,
                TypelessToken(generateActorToken(clientId, subject, samlToken.privateKey, samlToken.publicKey, samlToken.keyStore?.provider)),
                TokenTypeURI.JWT,
                if (refreshable) TokenTypeURI.REFRESH_TOKEN else TokenTypeURI.ACCESS_TOKEN,
                null
            )
        )

        val httpRequest: HTTPRequest = tokenRequest.toHTTPRequest()
        httpRequest.accept = MediaType.APPLICATION_JSON_VALUE
        httpRequest.connectTimeout = 30000
        httpRequest.readTimeout = 30000

        val response = TokenResponse.parse(httpRequest.send())
        if (!response.indicatesSuccess()) {
            val error = response.toErrorResponse().errorObject
            // eHealth 401/403 must not be confused with an authentication failure on FHC itself
            val status = if (error.httpStatusCode == HttpStatus.BAD_REQUEST.value()) HttpStatus.BAD_REQUEST else HttpStatus.BAD_GATEWAY
            throw IamTokenExchangeException(status, "IAM token exchange failed: ${error.code}${error.description?.let { " - $it" } ?: ""}")
        }

        val now = System.currentTimeMillis()
        val tokens = response.toSuccessResponse().tokens
        val refreshExpiresIn = (response.toSuccessResponse().customParameters["refresh_expires_in"] as? Number)?.toLong()
        return IamTokenResult(
            accessToken = tokens.accessToken.value,
            issuedTokenType = tokens.accessToken.issuedTokenType?.toString(),
            tokenType = tokens.accessToken.type.value,
            expiresIn = tokens.accessToken.lifetime.takeIf { it > 0 },
            refreshToken = tokens.refreshToken?.value,
            refreshExpiresIn = refreshExpiresIn,
            scope = tokens.accessToken.scope?.toString(),
            validity = tokens.accessToken.lifetime.takeIf { it > 0 }?.let { now + it * 1000 },
            refreshValidity = refreshExpiresIn?.takeIf { it > 0 }?.let { now + it * 1000 }
        )
    }

    /**
     * The actor token is the proof of possession of the holder-of-key: it must be signed with the private key bound to the SAML token.
     */
    fun generateActorToken(clientId: String, ssin: String, key: PrivateKey, publicKey: PublicKey? = null, provider: Provider? = null): String {
        val (algorithm, signer) = when (key.algorithm) {
            "RSA" -> Pair<JWSAlgorithm, JWSSigner>(JWSAlgorithm.RS256, RSASSASigner(key))
            "EC", "ECDSA" -> {
                // Opaque keys (remote cards, PKCS#11) do not expose their parameters: the curve is read from the certificate
                val curve = ((key as? ECPrivateKey)?.params ?: (publicKey as? ECPublicKey)?.params)?.let { Curve.forECParameterSpec(it) }
                    ?: throw IllegalArgumentException("Unsupported holder-of-key EC curve")
                Pair<JWSAlgorithm, JWSSigner>(ECDSA.resolveAlgorithm(curve), ECDSASigner(key, curve))
            }
            else -> throw IllegalArgumentException("Unsupported holder-of-key private key algorithm ${key.algorithm}")
        }
        if (key !is RSAPrivateKey && key !is ECPrivateKey && provider != null) {
            // Opaque keys can only be used through the provider of their keystore
            signer.jcaContext.provider = provider
        }
        val jwt = SignedJWT(
            JWSHeader.Builder(algorithm).type(JOSEObjectType.JWT).build(),
            JWTClaimsSet.Builder()
                .issuer(clientId)
                .subject(ssin)
                .audience(ACTOR_TOKEN_AUDIENCE)
                .issueTime(Date())
                .jwtID(UUID.randomUUID().toString())
                .build()
        )
        jwt.sign(signer)
        return jwt.serialize()
    }

    internal fun getCertificateHolderSsin(assertion: Element): String? {
        val attributes = assertion.getElementsByTagNameNS("*", "Attribute")
        return (0 until attributes.length).map { attributes.item(it) as Element }
            .find { (it.getAttribute("AttributeName").ifEmpty { it.getAttribute("Name") }) == CERTIFICATE_HOLDER_SSIN_ATTRIBUTE }
            ?.getElementsByTagNameNS("*", "AttributeValue")?.item(0)?.textContent?.trim()?.takeIf { it.isNotEmpty() }
    }

    private fun toXmlString(element: Element): String {
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")
        val writer = StringWriter()
        transformer.transform(DOMSource(element), StreamResult(writer))
        return writer.toString()
    }

    companion object {
        private const val ACTOR_TOKEN_AUDIENCE = "urn:be:fgov:ehealth:sts:1_0"
        private const val CERTIFICATE_HOLDER_SSIN_ATTRIBUTE = "urn:be:fgov:ehealth:1.0:certificateholder:person:ssin"
        private const val SAML1_ASSERTION_NS = "urn:oasis:names:tc:SAML:1.0:assertion"
        private const val SAML2_ASSERTION_NS = "urn:oasis:names:tc:SAML:2.0:assertion"
    }

}
