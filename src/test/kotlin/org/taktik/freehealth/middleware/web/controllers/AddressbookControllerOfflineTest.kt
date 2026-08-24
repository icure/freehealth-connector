package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.JsonParser
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import java.net.URI
import java.util.UUID

/**
 * Offline checks on /ab/search/hcp: criteria validation happens before any eHealth call, so no keystore, no token
 * and no network are needed. Also asserts that the endpoint is published in the Swagger descriptor.
 */
@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressbookControllerOfflineTest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private fun fakeAuthHeaders() = HttpHeaders().apply {
        set("X-FHC-keystoreId", UUID.randomUUID().toString())
        set("X-FHC-tokenId", UUID.randomUUID().toString())
        set("X-FHC-passPhrase", "not-a-real-passphrase")
    }

    // URI overload: the String overload treats the url as a template and would re-encode a literal % sequence
    private fun get(url: String) =
        restTemplate!!.exchange(URI.create(url), HttpMethod.GET, HttpEntity<Void>(fakeAuthHeaders()), String::class.java)

    @Test
    fun searchHcpIsExposedInSwagger() {
        val apiDocs = restTemplate!!.getForObject("http://localhost:$port/v2/api-docs", String::class.java)
        val operation = JsonParser().parse(apiDocs).asJsonObject
            .getAsJsonObject("paths").getAsJsonObject("/ab/search/hcp").getAsJsonObject("get")
        Assertions.assertThat(operation.get("summary").asString).isNotEmpty()
        Assertions.assertThat(operation.get("description").asString).contains("mutually exclusive")

        val parameters = operation.getAsJsonArray("parameters").map { it.asJsonObject }
        Assertions.assertThat(parameters.map { it.get("name").asString }).containsExactlyInAnyOrder(
            "X-FHC-keystoreId", "X-FHC-tokenId", "X-FHC-passPhrase",
            "lastName", "firstName", "profession", "nihii", "ssin", "zipCode", "city", "email", "offset", "limit"
        )
        Assertions.assertThat(parameters).allMatch { it.get("description").asString.isNotEmpty() }
        // @ApiParam defaults required to false, which would wrongly advertise the auth headers as optional
        Assertions.assertThat(parameters.filter { it.get("in").asString == "header" })
            .allMatch { it.get("required").asBoolean }
        Assertions.assertThat(parameters.filter { it.get("in").asString == "query" })
            .allMatch { !it.get("required").asBoolean }
    }

    @Test
    fun zipCodeAndCityAreMutuallyExclusive() {
        val response = get("http://localhost:$port/ab/search/hcp?lastName=Steeman&zipCode=1000&city=Bruxelles")
        Assertions.assertThat(response.statusCode.value()).isEqualTo(400)
        Assertions.assertThat(response.body).contains("mutually exclusive")
    }

    @Test
    fun nihiiAndSsinAreMutuallyExclusive() {
        val response = get("http://localhost:$port/ab/search/hcp?nihii=11111111111&ssin=22222222222")
        Assertions.assertThat(response.statusCode.value()).isEqualTo(400)
        Assertions.assertThat(response.body).contains("mutually exclusive")
    }

    @Test
    fun theEmptyQueryIsRejected() {
        val response = get("http://localhost:$port/ab/search/hcp")
        Assertions.assertThat(response.statusCode.value()).isEqualTo(400)
        Assertions.assertThat(response.body).contains("At least one search criterion")
    }

    @Test
    fun blankCriteriaCountAsAbsent() {
        val response = get("http://localhost:$port/ab/search/hcp?lastName=%20%20")
        Assertions.assertThat(response.statusCode.value()).isEqualTo(400)
        Assertions.assertThat(response.body).contains("At least one search criterion")
    }
}
