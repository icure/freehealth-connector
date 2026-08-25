/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.JsonParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration

/**
 * `/efact/flatcore` renders the flat file locally: no keystore, no token, no MyCareNet call. Used here to prove that
 * a physiotherapy invoice carrying an agreement number produces an ET 52 with the number in zone 19 (positions
 * 132-151), per INAMI annexe 6.8 / annexe 26.4.
 */
@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EfactFlatcoreOfflineTest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val agreementNumber = "30600000000000000103"

    /** readType 1 / deviceType 1: an eID chip reading. ET 52 Z 9 and Z 10, which annexe 26.4 always requires. */
    private val eidItem = """
        "eidItem": { "readDate": 20260729000000, "readHour": 1030, "readValue": "5910212346",
                     "readType": "1", "deviceType": "1", "vignetteReason": 0, "justificationDocumentNumber": 1 }
    """.trimIndent()

    private fun batch(itemExtras: String) = """
        {
          "invoicingYear": 2026, "invoicingMonth": 7,
          "batchRef": "TESTBATCH0001", "uniqueSendNumber": 1, "numericalRef": 1,
          "ioFederationCode": "300", "invoiceContent": 40,
          "sender": {
            "nihii": 54123456789, "bce": 999999922, "ssin": "12345678901",
            "firstName": "Jean", "lastName": "Kine", "phoneNumber": 32470000000, "conventionCode": 0,
            "professionCode": 50
          },
          "invoices": [{
            "ioCode": "306", "invoiceNumber": 1, "invoiceRef": "TESTINV0001", "reason": "Other",
            "patient": { "ssin": "86103130262", "firstName": "Test", "lastName": "Patient" },
            "items": [{
              "codeNomenclature": 567011, "dateCode": 20260729, "reimbursedAmount": 1000,
              "doctorIdentificationNumber": "54123456789"$itemExtras
            }]
          }]
        }
    """.trimIndent()

    private fun post(body: String) = restTemplate!!.postForEntity(
        "http://localhost:$port/efact/flatcore",
        HttpEntity(body, HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }),
        String::class.java)

    private fun flatcore(body: String): List<String> {
        val response = post(body)
        assertThat(response.statusCode.value()).isEqualTo(200)
        val flatFile = JsonParser().parse(response.body).asJsonObject.get("flatFile").asString
        return flatFile.chunked(350).also { records -> assertThat(records).allMatch { it.length == 350 } }
    }

    @Test
    fun flatcoreNeedsNoAuthenticationAndEmitsNoRecord52WithoutAgreementNumber() {
        assertThat(flatcore(batch("")).map { it.take(2) }).containsExactly("10", "20", "50", "80", "90")
    }

    @Test
    fun anAgreementNumberEmitsARecord52CarryingItInZone19() {
        val records = flatcore(batch(""", "agreementNumber": "$agreementNumber", $eidItem"""))

        assertThat(records.map { it.take(2) }).containsExactly("10", "20", "50", "52", "80", "90")
        val record52 = records.single { it.startsWith("52") }
        assertThat(record52.substring(131, 151)).isEqualTo(agreementNumber)
    }

    /**
     * Annexe 26.4 makes ET 52 Z 9 and Z 10 mandatory with no exception clause, so a physiotherapy batch cannot carry
     * the agreement number alone. The 400 body must name the rule and must not echo the number back.
     */
    @Test
    fun anAgreementNumberWithoutEidIsRefusedWithoutEchoingTheNumber() {
        val response = post(batch(""", "agreementNumber": "$agreementNumber""""))

        assertThat(response.statusCode.value()).isEqualTo(400)
        assertThat(response.body).contains("annexe 26.4").doesNotContain(agreementNumber)
    }

    @Test
    fun aMalformedAgreementNumberIsRefusedWithoutEchoingTheNumber() {
        val response = post(batch(""", "agreementNumber": "306", $eidItem"""))

        assertThat(response.statusCode.value()).isEqualTo(400)
        assertThat(response.body).contains("expected exactly 20 digits")
    }

    @Test
    fun insuranceRefStillEmitsRecord51AndNotRecord52() {
        val records = flatcore(batch(""", "insuranceRef": "1234567890", "insuranceRefDate": 20260729"""))

        assertThat(records.map { it.take(2) }).containsExactly("10", "20", "50", "51", "80", "90")
    }
}
