package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.bouncycastle.cert.X509CertificateHolder
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cms.CMSEnvelopedDataParser
import org.bouncycastle.cms.CMSProcessable
import org.bouncycastle.cms.CMSProcessableByteArray
import org.bouncycastle.cms.CMSSignedData
import org.bouncycastle.cms.CMSSignerDigestMismatchException
import org.bouncycastle.cms.SignerId
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient
import org.bouncycastle.util.Selector
import org.jose4j.jwt.consumer.JwtConsumerBuilder
import org.springframework.stereotype.Service
import org.taktik.freehealth.middleware.domain.rsw.Jwks
import org.taktik.freehealth.middleware.domain.rsw.Jwt
import org.taktik.freehealth.middleware.domain.rsw.RswEndpoints
import org.taktik.freehealth.middleware.service.RswFhirService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.icure.fhir.entities.r4.binary.Binary
import org.taktik.icure.fhir.entities.r4.bundle.Bundle
import org.taktik.icure.fhir.entities.r4.operationoutcome.OperationOutcome
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.security.PublicKey
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.Base64
import java.util.UUID
import javax.mail.Multipart
import javax.mail.Session
import javax.mail.internet.MimeMessage

fun HttpURLConnection.postForm(form: List<Pair<String, String>>): InputStream? {
    setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    requestMethod = "POST"
    doOutput = true
    outputStream.bufferedWriter().use {
        it.write(form.joinToString("&") { (k, v) -> "${k}=${URLEncoder.encode(v, Charsets.UTF_8.name())}" })
    }
    return if (responseCode == 200) inputStream else null
}

@Service
class RswFhirServiceImpl(val stsService: STSService) : RswFhirService {
    private val certFactory: CertificateFactory = CertificateFactory.getInstance("X.509")
    private val issuer = "idp.rsw.vault"
    private val audience = "api.rsw.clientid.fhir.careset"
    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())
    private var rswPublicKey: PublicKey? = null
    private val fhirSearchEndpoint = "https://www.ehealth.fgov.be/standards/fhir/NamingSystem/ssin|"

    override fun search(nihii: String,
        clientId: String,
        clientType: String,
        clientSecret: String,
        keystoreId: UUID,
        passPhrase: String,
        patientSsin: String): Bundle {

        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val privateKey = hokPrivateKeys.entries.mapNotNull { it.value }.firstOrNull()

        val accessToken = obtainAccessToken(clientId, clientSecret, nihii) ?: throw IllegalArgumentException("Cannot get access token")
        val bundle = loadFHIRSearchResult(clientType, nihii, accessToken, patientSsin) ?: throw IllegalArgumentException("Cannot load fhir")

        val multipart = Base64.getDecoder().decode(bundle.entry.mapNotNull { it.resource as? Binary }.first().data!!)

        val session = Session.getDefaultInstance(System.getProperties(), null)
        return (MimeMessage(session, ByteArrayInputStream(multipart)).content as? Multipart)?.let { msg ->
            val bytes = msg.getBodyPart(0).inputStream.readBytes()
            val signedContent: CMSProcessable = CMSProcessableByteArray(bytes)
            val signedData = CMSSignedData(signedContent, msg.getBodyPart(1).inputStream)

            @Suppress("UNUSED_VARIABLE")
            val signed = signedData.signerInfos.signers.any { signer ->
                data class X509CertificateHolderSelector(val sid: SignerId) : Selector<X509CertificateHolder> {
                    override fun match(obj: X509CertificateHolder) = true //TODO select the right one
                    override fun clone() = this.copy()
                }
                signedData.certificates.getMatches(X509CertificateHolderSelector(signer.sid)).any { ch ->
                    val cert: X509Certificate = JcaX509CertificateConverter().setProvider("BC").getCertificate(ch)
                    try { signer.verify(JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert)) } catch(e:CMSSignerDigestMismatchException) {
                        false // <- org.bouncycastle.cms.CMSSignerDigestMismatchException: message-digest attribute value does not match calculated value
                    }
                }
            }

            val parser = CMSEnvelopedDataParser(bytes)

            val recInfo = parser.recipientInfos.recipients.firstOrNull()
            val recipient = JceKeyTransEnvelopedRecipient(privateKey!!)

            val content = recInfo?.getContentStream(recipient)?.contentStream?.readBytes() //<-- Error here: bad padding: data hash wrong
            (MimeMessage(session, ByteArrayInputStream(content)).content as? Multipart)?.let { objectMapper.readValue(it.getBodyPart(0).inputStream.readBytes(), Bundle::class.java) }
        } ?: throw IllegalStateException("Cannot decode data")
    }

    private fun getJwtTokenContext(rswPublicKey: PublicKey, jwtToken: String) = JwtConsumerBuilder()
        .setRequireJwtId().setRequireNotBefore()
        .setRequireExpirationTime().setExpectedAudience(true, audience)
        .setVerificationKey(rswPublicKey).setExpectedIssuer(issuer)
        .setAllowedClockSkewInSeconds(120).build().process(jwtToken) //Will throw an exception if invalid

    private fun loadFHIRSearchResult(clientType: String,
        nihii: String,
        accessToken: String,
        patientSsin: String): Bundle? {
        return objectMapper.readValue(
            (URL("https://jacc.reseausantewallon.be/proxy/fhir/caresets/AllergyIntolerance?patient:Patient.identifier=${
                URLEncoder.encode(fhirSearchEndpoint, Charsets.UTF_8.name())
            }${patientSsin}").openConnection() as? HttpURLConnection)?.apply {
                requestMethod = "GET"
                setRequestProperty("Accept", "application/json")
                setRequestProperty("SENDER-ENCRYPTION-ACTOR-ID", nihii)
                setRequestProperty("SENDER-ENCRYPTION-ACTOR-TYPE", clientType)
                setRequestProperty("SENDER-ENCRYPTION-ACTOR-APPLICATION", "")
                setRequestProperty("Authorization", "Bearer $accessToken")
            }?.let {
                if (it.responseCode != 200) {
                    val diagnostics = objectMapper.readValue(it.errorStream.readBytes(), Bundle::class.java).entry.map { it.resource }.let {
                        it.filterIsInstance(OperationOutcome::class.java).firstOrNull()?.issue?.firstOrNull()?.diagnostics ?:
                        it.filterIsInstance(Binary::class.java).firstOrNull()?.data?.let { Base64.getDecoder().decode(it).let {
                            try { objectMapper.readValue(it, OperationOutcome::class.java).issue.firstOrNull()?.diagnostics } catch (e:Exception) { it.toString(Charsets.UTF_8) }
                        } }
                    } ?: it.responseMessage
                    throw RuntimeException("Rsw Vault Fhir Api Call Failed : HTTP Error code : ${it.responseCode} - $diagnostics")
                } else {
                    it.inputStream.readBytes()
                }
            } ?: throw RuntimeException("Cannot create Fhir Api URL"), Bundle::class.java)
        }

    private fun obtainAccessToken(clientId: String, clientSecret: String, nihii: String) =
        (URL("https://jacc.reseausantewallon.be/is4acc/careset/v1/token").openConnection() as? HttpURLConnection)?.postForm( //Request token
            listOf(
                "grant_type" to "client_credentials",
                "scope" to audience,
                "client_id" to clientId,
                "client_secret" to clientSecret,
                "client_nihii" to nihii
                  ))?.let {
            val readText = it.bufferedReader().readText()
            objectMapper.readValue(readText, Jwt::class.java)?.accessToken?.also { at -> getJwtTokenContext(fetchRSWPublicKey(), at) }
        }

    private fun fetchRSWPublicKey(): PublicKey =
        rswPublicKey ?: objectMapper.readValue(URL("https://jacc.reseausantewallon.be/is4acc/.well-known/openid-configuration").readBytes(), //load endpoints
                                               RswEndpoints::class.java)?.let { endpoints ->
            objectMapper.readValue(URL(endpoints.jwks_uri).readBytes(), Jwks::class.java)
                ?.let { it.keys?.firstOrNull()?.x5c?.joinToString("") }
                ?.let { k -> //Load keys
                    (certFactory.generateCertificate(ByteArrayInputStream(Base64.getDecoder().decode(k))) as X509Certificate).publicKey
                }
        }?.also { rswPublicKey = it } ?: throw IllegalStateException("Cannot get RSW public key")
}
