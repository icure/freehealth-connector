package org.taktik.freehealth.middleware.web.controllers

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
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
import java.io.StringWriter
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.Marshaller

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MediprimaControllerTest: EhealthTest()  {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    val todayDate = today.format(formatter)

    @Test
    fun consultCaremedData_sc1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"85000132773"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
                "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"66092552676"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"66092552676"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"66092552676"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc5() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"66092552676"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc6() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"97000080964"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc7() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"95060180934"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc8() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"79020561139"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc9() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"92093065750"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc10() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"95061847255"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc11() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"09010240155"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultCaremedData_sc12() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)
        val response = this.restTemplate.exchange("http://localhost:$port/mediprima/consultMediprima/${"97091669156"}?hcpQuality=${"doctor"}&hcpNihii=$nihii6&hcpSsin=$ssin6&hcpName=$name6" +
            "&passPhrase=$passPhrase&startDate=20250409&endDate=20250409&referenceDate=$todayDate",
            HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase)
        println("Result: "+ ObjectMapper().registerModule(KotlinModule()).writeValueAsString(response))
    }

    @Test
    fun consultTarif_sc1() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

            val url = "http://localhost:$port/mediprima/consultTarificationMediprima/66092552676?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=20250318"
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

    @Test
    fun consultTarif_sc2() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/66092552676?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
        val codes = listOf("101075")

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

    @Test
    fun consultTarif_sc3() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/66092552676?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
        val codes = listOf("560011")

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

    @Test
    fun consultTarif_sc4() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/66092552676?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
        val codes = listOf("102034")

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

    @Test
    fun consultTarif_sc5() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/66092552676?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=20250318"
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

    @Test
    fun consultTarif_sc6() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/66092552676?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=20250718"
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

    @Test
    fun consultTarif_sc7() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/66092552676?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc8() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/97000080964?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc9() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/01102248542?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc10() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/79020561139?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc11() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/92093065750?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc12() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/95061847255?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc13() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/09010240155?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc14() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/97091669156?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
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

    @Test
    fun consultTarif_sc15() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin6!!, password6!!)

        val url = "http://localhost:$port/mediprima/consultTarificationMediprima/09010240155?hcpFirstName=${"Maxime"}&hcpLastName=${"Mennechet"}&hcpNihii=$nihii6&hcpSsin=$ssin6&passPhrase=$passPhrase&date=$todayDate"
        val codes = listOf("101032", "475075")

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
