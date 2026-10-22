/*
 *
 * Copyright (C) 2018 iCure SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.service.impl

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSVerifier
import com.nimbusds.jose.crypto.ECDSAVerifier
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.SignedJWT
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import org.assertj.core.api.Assertions.assertThat
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.junit.Test
import org.mockito.Mockito.mock
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.dao.KeystoreProviderService
import org.taktik.freehealth.middleware.service.STSService
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.ECGenParameterSpec
import java.util.Date
import java.util.UUID
import javax.xml.parsers.DocumentBuilderFactory

class SSOServiceImplActorTokenTest {
    private val service = SSOServiceImpl(mock(STSService::class.java), mock(KeystoreProviderService::class.java))

    private fun checkActorToken(token: String, verifier: JWSVerifier, expectedAlgorithm: JWSAlgorithm) {
        val jwt = SignedJWT.parse(token)
        assertThat(jwt.verify(verifier)).isTrue()
        assertThat(jwt.header.algorithm).isEqualTo(expectedAlgorithm)
        assertThat(jwt.header.type.type).isEqualTo("JWT")
        assertThat(jwt.jwtClaimsSet.issuer).isEqualTo("medispring")
        assertThat(jwt.jwtClaimsSet.subject).isEqualTo("72070539942")
        assertThat(jwt.jwtClaimsSet.audience).containsExactly("urn:be:fgov:ehealth:sts:1_0")
        assertThat(jwt.jwtClaimsSet.issueTime).isNotNull()
        assertThat(jwt.jwtClaimsSet.jwtid).isNotBlank()
    }

    @Test
    fun actorTokenIsSignedWithRsaKey() {
        val keyPair = KeyPairGenerator.getInstance("RSA").apply { initialize(2048) }.generateKeyPair()
        val token = service.generateActorToken("medispring", "72070539942", keyPair.private)
        checkActorToken(token, RSASSAVerifier(keyPair.public as RSAPublicKey), JWSAlgorithm.RS256)
    }

    @Test
    fun actorTokenIsSignedWithP256Key() {
        val keyPair = KeyPairGenerator.getInstance("EC").apply { initialize(ECGenParameterSpec("secp256r1")) }.generateKeyPair()
        val token = service.generateActorToken("medispring", "72070539942", keyPair.private)
        checkActorToken(token, ECDSAVerifier(keyPair.public as ECPublicKey), JWSAlgorithm.ES256)
    }

    @Test
    fun actorTokenIsSignedWithP384Key() {
        val keyPair = KeyPairGenerator.getInstance("EC").apply { initialize(ECGenParameterSpec("secp384r1")) }.generateKeyPair()
        val token = service.generateActorToken("medispring", "72070539942", keyPair.private)
        checkActorToken(token, ECDSAVerifier(keyPair.public as ECPublicKey), JWSAlgorithm.ES384)
    }

    private fun pkcs12Credential(keyAlgorithm: String, keyGenSpec: (KeyPairGenerator) -> Unit, signatureAlgorithm: String): KeyStoreCredential {
        val keyPair = KeyPairGenerator.getInstance(keyAlgorithm).apply(keyGenSpec).generateKeyPair()
        val name = X500Name("CN=SSIN=72070539942")
        val certificate = JcaX509CertificateConverter().getCertificate(
            JcaX509v3CertificateBuilder(name, BigInteger.ONE, Date(), Date(System.currentTimeMillis() + 86400000), name, keyPair.public)
                .build(JcaContentSignerBuilder(signatureAlgorithm).build(keyPair.private))
        )
        val data = ByteArrayOutputStream().also { out ->
            KeyStore.getInstance("PKCS12").apply {
                load(null, null)
                setKeyEntry("authentication", keyPair.private, "secret".toCharArray(), arrayOf(certificate))
            }.store(out, "secret".toCharArray())
        }.toByteArray()
        val keyStore = KeyManager.getKeyStore(ByteArrayInputStream(data), "PKCS12", "secret".toCharArray())
        return KeyStoreCredential(UUID.randomUUID(), keyStore, "authentication", "secret", "doctor")
    }

    @Test
    fun actorTokenIsSignedWithRsaKeyFromPkcs12Keystore() {
        val credential = pkcs12Credential("RSA", { it.initialize(2048) }, "SHA256withRSA")
        val token = service.generateActorToken("medispring", "72070539942", credential.privateKey, credential.publicKey, credential.keyStore.provider)
        checkActorToken(token, RSASSAVerifier(credential.certificate.publicKey as RSAPublicKey), JWSAlgorithm.RS256)
    }

    @Test
    fun actorTokenIsSignedWithEcKeyFromPkcs12Keystore() {
        val credential = pkcs12Credential("EC", { it.initialize(ECGenParameterSpec("secp256r1")) }, "SHA256withECDSA")
        val token = service.generateActorToken("medispring", "72070539942", credential.privateKey, credential.publicKey, credential.keyStore.provider)
        checkActorToken(token, ECDSAVerifier(credential.certificate.publicKey as ECPublicKey), JWSAlgorithm.ES256)
    }

    @Test
    fun certificateHolderSsinIsExtractedFromSaml1Assertion() {
        val xml = """
            <saml:Assertion xmlns:saml="urn:oasis:names:tc:SAML:1.0:assertion" AssertionID="_1" MajorVersion="1" MinorVersion="1">
              <saml:AttributeStatement>
                <saml:Attribute AttributeName="urn:be:fgov:person:ssin" AttributeNamespace="urn:be:fgov:identification-namespace">
                  <saml:AttributeValue>11111111111</saml:AttributeValue>
                </saml:Attribute>
                <saml:Attribute AttributeName="urn:be:fgov:ehealth:1.0:certificateholder:person:ssin" AttributeNamespace="urn:be:fgov:identification-namespace">
                  <saml:AttributeValue> 72070539942 </saml:AttributeValue>
                </saml:Attribute>
              </saml:AttributeStatement>
            </saml:Assertion>
        """.trimIndent()
        val assertion = DocumentBuilderFactory.newInstance().apply { isNamespaceAware = true }
            .newDocumentBuilder().parse(ByteArrayInputStream(xml.toByteArray())).documentElement

        assertThat(service.getCertificateHolderSsin(assertion)).isEqualTo("72070539942")
    }
}
