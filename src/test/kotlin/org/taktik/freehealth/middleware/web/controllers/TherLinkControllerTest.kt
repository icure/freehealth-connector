/*
 *
 * Copyright (C) 2018 Taktik SA
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
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import java.util.*

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TherLinkControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())

    private fun getTherapeuticLink(): TherapeuticLinkDto {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therLink = this.restTemplate.exchange("http://localhost:$port/therlink/${"74010414733"}/$nihii1?hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientSsin=${"74010414733"}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
        assertThat(therLink != null)
        val tlmsg: TherapeuticLinkMessageDto = objectMapper.readValue(therLink.body, TherapeuticLinkMessageDto::class.java)
        assertThat(tlmsg.therapeuticLinks.size).isGreaterThan(0)
        return tlmsg.therapeuticLinks.first()
    }

    @Test
    fun getTherLink() {
        val therapeuticLink = this.getTherapeuticLink();
        assertThat(therapeuticLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
        assertThat(therapeuticLink?.type).isEqualToIgnoringCase("gpconsultation")
        assertThat((therapeuticLink?.endDate ?: 0) - (therapeuticLink?.startDate ?: 0)).isGreaterThan(10000L)
    }

    @Test
    fun doesTherLinkExist() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therapeuticLink = this.getTherapeuticLink();

        val exists = this.restTemplate.exchange("http://localhost:$port/therlink/check",HttpMethod.POST, HttpEntity(therapeuticLink!!, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        val existingLink = objectMapper.readValue(exists.body, TherapeuticLinkDto::class.java)
        assertThat(existingLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
        assertThat(existingLink?.type).isEqualToIgnoringCase("gpconsultation")
        assertThat((existingLink?.endDate ?: 0) - (existingLink?.startDate ?: 0)).isGreaterThan(10000L)
    }

    @Test
    fun revokeTherLink() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therLink = this.restTemplate.exchange("http://localhost:$port/therlink/${"74010414733"}/$nihii1?hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}", HttpMethod.GET, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
        assertThat(therLink != null)
        val tlmsg: TherapeuticLinkMessageDto = objectMapper.readValue(therLink.body, TherapeuticLinkMessageDto::class.java)
        val therapeuticLink = if(tlmsg.therapeuticLinks.isEmpty()) {
            this.restTemplate.exchange("http://localhost:$port/therlink/register?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientSsin=${"74010414733"}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&proofType=EIDENCODING_HOUSECALL", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1).body?.let {objectMapper.readValue(it, TherapeuticLinkMessageDto::class.java)}?.let {it.therapeuticLinks.firstOrNull()}
        } else tlmsg.therapeuticLinks.first()
        assertThat(therapeuticLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
        val revoke= this.restTemplate.exchange("http://localhost:$port/therlink/revoke?proofType=EIDENCODING_HOUSECALL", HttpMethod.POST, HttpEntity(therapeuticLink!!, createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java)
        assertThat(revoke.body.length).isGreaterThan(0)
        //Recreate link

    }

    @Test
    fun registerTherLink() {
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val therLink = this.restTemplate.exchange("http://localhost:$port/therlink/register?hcpNihii=$nihii1&hcpSsin=$ssin1&hcpFirstName={firstName}&hcpLastName={lastName}&patientSsin=${"74010414733"}&patientFirstName=${"Antoine"}&patientLastName=${"Duchâteau"}&eidCardNumber=${"592363302467"}&proofType=EIDENCODING_HOUSECALL", HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, firstName1, lastName1)
        assertThat(therLink != null)
        val tlmsg = objectMapper.readValue(therLink.body, TherapeuticLinkMessageDto::class.java)
        val therapeuticLink = tlmsg.therapeuticLinks.first();
        assertThat(therapeuticLink?.patient?.inss).isEqualToIgnoringCase("74010414733")
    }
}
