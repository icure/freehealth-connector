package org.taktik.freehealth.middleware.web.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MediprimaControllerTest: EhealthTest()  {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun consultCaremedData() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"95060180934"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
                "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=20250509",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }
}
