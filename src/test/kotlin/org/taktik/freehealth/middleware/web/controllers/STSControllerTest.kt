package org.taktik.freehealth.middleware.web.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
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

    @Test
    fun checkKeystoreExist(){
        val keystoreId = uploadKeystore((MyTestsConfiguration::class).java.getResource("${ssin1}.acc-p12").path, port, restTemplate!!)

        val res = this.restTemplate?.getForObject("http://localhost:$port/sts/checkKeystore/${keystoreId}", String::class.java)
        assertThat(res != null)
    }
}
