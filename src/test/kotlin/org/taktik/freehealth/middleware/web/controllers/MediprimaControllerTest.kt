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
import org.springframework.http.MediaType
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import java.time.Instant

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MediprimaControllerTest: EhealthTest()  {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val tarificationNisses = listOf("66092552676", "97000080964", "95060180934", "01102248542", "79020561139", "95061847255", "09010240155", "97091669156", "92093065750", "85000132773", "74032853407")

    @Test
    fun consultCaremedData() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"95060180934"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
                "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=20250509",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultTarif() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        tarificationNisses.map{
            val url = "http://localhost:$port/mediprima/consultTarificationMediprima/$it?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=20250618"
            val codes = listOf("101032")

            val headers = createHeaders(null, null, keystoreId, tokenId, passPhrase).apply {
                contentType = MediaType.APPLICATION_JSON
            }

            // Crée la requête avec les codes dans le body
            val entity = HttpEntity(codes, headers)

            // Envoie la requête
            val response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String::class.java
            )

            println("Result: " + ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
        }
    }
}
