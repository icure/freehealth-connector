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

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.domain.common.GenAsyncResponse
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataBatchRequest
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataList
import org.taktik.freehealth.middleware.dto.memberdata.FacetDto
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import org.taktik.freehealth.middleware.dto.memberdata.MemberDataBatchRequestDto
import org.taktik.freehealth.middleware.service.MemberDataService
import org.taktik.icure.cin.saml.extensions.Facet
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID

@RestController
@RequestMapping("/mda")
@Tag(name = "MemberData", description = "Member data operations for retrieving insurance membership information from the MyCareNet platform. Supports both synchronous and asynchronous queries by patient SSIN or insurance organization membership number.")
class MemberDataController(val memberDataService: MemberDataService, val mapper: MapperFacade) {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"

    @Operation(
        summary = "Query member data by SSIN with facets",
        description = "Retrieves insurance membership information for a patient identified by their SSIN, with specific query facets provided in the request body. Facets allow filtering the response to include only specific categories of membership data."
    )
    @PostMapping("/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun queryMemberData(
        @PathVariable ssin: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) endDate: Long?,
        @RequestParam(required = false) hospitalized: Boolean?,
        @RequestParam(required = false) requestType: String?,
        @RequestBody facets:List<FacetDto>
                     ) : MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
                                               tokenId = tokenId,
                                               hcpQuality = hcpQuality ?: "doctor",
                                               hcpNihii = hcpNihii,
                                               hcpSsin = hcpSsin ?: null,
                                               hcpName = hcpName,
                                               passPhrase = passPhrase,
                                               patientSsin = ssin,
                                               io = null,
                                               ioMembership = null,
                                               startDate = startDate,
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                                requestType= requestType,
                                                facets = facets.map { mapper.map(it, Facet::class.java) })
    }

    @Operation(
        summary = "Get member data by SSIN",
        description = "Retrieves insurance membership information for a patient identified by their SSIN. Returns general insurability data including the patient's insurance organization, coverage status, and related details."
    )
    @GetMapping("/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMemberData(
        @PathVariable ssin: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) endDate: Long?,
        @RequestParam(required = false) hospitalized: Boolean?,
        @RequestParam(required = false) requestType: String?
    ) : MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
                                               tokenId = tokenId,
                                               hcpQuality = hcpQuality ?: "doctor",
                                               hcpNihii = hcpNihii,
                                               hcpSsin = hcpSsin ?: null,
                                               hcpName = hcpName,
                                               passPhrase = passPhrase,
                                               patientSsin = ssin,
                                               io = null,
                                               ioMembership = null,
                                               startDate = startDate,
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                               hospitalized = hospitalized ?: false,
                                               requestType= requestType)
    }

    @Operation(
        summary = "Query member data by insurance membership with facets",
        description = "Retrieves insurance membership information for a patient identified by their insurance organization code and membership number, with specific query facets provided in the request body."
    )
    @PostMapping("/{io}/{ioMembership}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun queryMemberDataByMembership(
        @PathVariable io: String,
        @PathVariable ioMembership: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) endDate: Long?,
        @RequestParam(required = false) hospitalized: Boolean?,
        @RequestParam(required = false) requestType: String?,
        @RequestBody facets:List<FacetDto>
                       ) : MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
                                               tokenId = tokenId,
                                               hcpQuality = hcpQuality ?: "doctor",
                                               hcpNihii = hcpNihii,
                                               hcpSsin = hcpSsin ?: null,
                                               hcpName = hcpName,
                                               passPhrase = passPhrase,
                                               patientSsin = null,
                                               io = io,
                                               ioMembership = ioMembership,
                                               startDate = startDate,
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                                requestType= requestType,
            facets = facets.map { mapper.map(it, Facet::class.java) })
    }

    @Operation(
        summary = "Get member data by insurance membership",
        description = "Retrieves insurance membership information for a patient identified by their insurance organization code and membership number. Returns general insurability data including coverage status and related details."
    )
    @GetMapping("/{io}/{ioMembership}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMemberDataByMembership(
        @PathVariable io: String,
        @PathVariable ioMembership: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) endDate: Long?,
        @RequestParam(required = false) hospitalized: Boolean?,
        @RequestParam(required = false) requestType: String?
    ): MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
                                               tokenId = tokenId,
                                               hcpQuality = hcpQuality ?: "doctor",
                                               hcpNihii = hcpNihii,
                                               hcpSsin = hcpSsin ?: null,
                                               hcpName = hcpName,
                                               passPhrase = passPhrase,
                                               patientSsin = null,
                                               io = io,
                                               ioMembership = ioMembership,
                                               startDate = startDate,
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                               hospitalized = hospitalized ?: false,
                                               requestType= requestType)
    }

    @Operation(
        summary = "Send an asynchronous member data request",
        description = "Submits a batch member data request asynchronously to the MyCareNet platform. The response contains a reference that can be used to retrieve the results later via the async messages endpoint."
    )
    @PostMapping("/async/request", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMemberDataRequestAsync(
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) endDate: Long?,
        @RequestParam(required = false) hospitalized: Boolean?,
        @RequestParam(required = false) requestType: String?,
        @RequestBody mdaRequest: MemberDataBatchRequestDto
                             ): GenAsyncResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.sendMemberDataRequest(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality ?: "medicalhouse",
            hcpNihii = hcpNihii,
            hcpName = hcpName,
            hcpSsin = hcpSsin,
            startDate = startDate,
            endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
            passPhrase = passPhrase,
            hospitalized = hospitalized,
            requestType = requestType ?: "information",
            mdaRequest = mapper.map(mdaRequest, MemberDataBatchRequest::class.java)
                                                      )
    }

    @Operation(
        summary = "Retrieve asynchronous member data messages",
        description = "Retrieves the response messages for previously submitted asynchronous member data requests from the MyCareNet platform, optionally filtered by message names."
    )
    @PostMapping("/async/messages", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMemberDataMessageAsync(
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam messageNames: List<String>?) : MemberDataList? {
            return memberDataService.getMemberDataMessages(
                keystoreId = keystoreId,
                tokenId = tokenId,
                passPhrase = passPhrase,
                hcpQuality = hcpQuality,
                hcpNihii = hcpNihii,
                hcpSsin = hcpSsin,
                hcpName = hcpName,
                messageNames = messageNames)
        }

    @Operation(
        summary = "Confirm asynchronous member data messages",
        description = "Confirms the receipt of asynchronous member data response messages from the MyCareNet platform, identified by their reference strings. This prevents the same messages from being returned in subsequent retrieval calls."
    )
    @PostMapping("/async/confirm/messages", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmMemberDataMessagesAsync(
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestBody mdaMessagesReference: List<String>) : Boolean?{
        return memberDataService.confirmMemberDataMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpName = hcpName,
            hcpSsin = hcpSsin,
            mdaMessagesReference = mdaMessagesReference
        )
        }


    @Operation(
        summary = "Confirm asynchronous member data acknowledgements",
        description = "Confirms the receipt of asynchronous member data acknowledgement messages from the MyCareNet platform, identified by their hash values. This prevents the same acknowledgements from being returned in subsequent retrieval calls."
    )
    @PostMapping("/async/confirm/acks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmMemberDataAcksAsync(
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestBody mdaAcksHashes: List<String>): Boolean?{
        return memberDataService.confirmMemberDataAcks(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpName = hcpName,
            hcpSsin = hcpSsin,
            mdaAcksHashes = mdaAcksHashes
        )
    }

}
