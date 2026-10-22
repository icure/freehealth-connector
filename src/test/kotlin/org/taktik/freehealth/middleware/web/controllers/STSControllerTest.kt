package org.taktik.freehealth.middleware.web.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.nimbusds.jwt.SignedJWT
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assume
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.sts.IamTokenResult
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class STSControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null
    private val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())

    @Value("\${org.taktik.icure.iam.clientId:}")
    private val iamClientId: String? = null

    @Test
    fun uploadKeystore() {
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("$ssin1.acc-p12").path, port, restTemplate!!)
        assertThat(keystoreId != null)
    }

    @Test
    fun requestToken() {
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("${ssin1}.acc-p12").path, port, restTemplate!!)
        val ssin = ssin1
        val passPhrase = password1
        val res = this.restTemplate.exchange("http://localhost:$port/sts/token?ssin=$ssin", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, null, passPhrase)), String::class.java).body
        assertThat(res != null)
        val saml = objectMapper.readValue(res, SamlTokenResult::class.java)
        assertThat(saml.tokenId).isNotNull()
        assertThat(saml.token).startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Assertion")
        assertThat(saml.token).containsIgnoringCase("OU=${firstName1} ${lastName1}")
    }

    @Test
    fun registerToken() {
    }

    private fun postTokenExchangeForIam(refreshable: Boolean, ssin: String? = null): ResponseEntity<String> {
        Assume.assumeFalse("org.taktik.icure.iam.clientId is not set", iamClientId.isNullOrBlank())
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        return this.restTemplate.exchange(
            "http://localhost:$port/sts/token/exchange/iam?clientId=$iamClientId&refreshable=$refreshable${ssin?.let { "&ssin=$it" } ?: ""}",
            HttpMethod.POST,
            HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)),
            String::class.java
        )
    }

    private fun exchangeTokenForIam(refreshable: Boolean): IamTokenResult {
        val res = postTokenExchangeForIam(refreshable)
        assertThat(res.statusCode.is2xxSuccessful).withFailMessage("IAM token exchange failed: ${res.body}").isTrue()
        return objectMapper.readValue(res.body, IamTokenResult::class.java)
    }

    @Test
    fun exchangeTokenForIamIsRejectedWhenActorTokenSubjectDoesNotMatch() {
        val res = postTokenExchangeForIam(false, "00000000097")
        assertThat(res.statusCode.value()).isEqualTo(400)
        assertThat(res.body).contains("invalid_token").contains("invalid actor_token")
    }

    @Test
    fun exchangeTokenForIamAccessToken() {
        val result = exchangeTokenForIam(false)
        assertThat(result.accessToken).isNotBlank()
        assertThat(result.tokenType).isEqualToIgnoringCase("Bearer")
        assertThat(result.validity).isGreaterThan(System.currentTimeMillis())
        assertThat(SignedJWT.parse(result.accessToken).jwtClaimsSet.issuer).isEqualTo("https://api-acpt.ehealth.fgov.be/auth/realms/healthcare")
    }

    @Test
    fun exchangeTokenForIamRefreshToken() {
        val result = exchangeTokenForIam(true)
        assertThat(result.accessToken).isNotBlank()
        assertThat(result.refreshToken).isNotBlank()
        assertThat(result.refreshValidity).isGreaterThan(System.currentTimeMillis())
    }

    @Test
    fun checkKeystoreExist(){
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("${ssin1}.acc-p12").path, port, restTemplate!!)

        val res = this.restTemplate?.getForObject("http://localhost:$port/sts/checkKeystore/${keystoreId}", String::class.java)
        assertThat(res != null)
    }
}
