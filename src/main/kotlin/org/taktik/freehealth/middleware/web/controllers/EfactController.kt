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
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.efact.InvoicesBatch
import org.taktik.freehealth.middleware.service.EfactService
import java.util.*

@RestController
@RequestMapping("/efact")
@Tag(name = "Efact", description = "Electronic invoicing (tiers payant) operations. Allows healthcare providers to send electronic invoices directly to insurance organizations via the MyCareNet platform.")
class EfactController(val efactService: EfactService, val mapper: MapperFacade) {
    @Operation(
        summary = "Send an invoice batch",
        description = "Sends a batch of electronic invoices to the MyCareNet platform for processing by the insurance organizations."
    )
    @PostMapping("/batch", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendBatch(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody batch: InvoicesBatch
                 ) =
        efactService.sendBatch(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            batch = batch
        )

    @Operation(
        summary = "Generate a flat file from an invoice batch",
        description = "Converts an invoice batch into the flat file format required by the MyCareNet platform, without sending it. Useful for previewing or archiving the generated content."
    )
    @PostMapping("/flat", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun makeFlatFile(
        @RequestBody batch: InvoicesBatch
                    ) =
        efactService.makeFlatFile(
            batch = batch,
            isTest = false,
            isMediprima = false
                                 )

    @Operation(
        summary = "Generate a flat file core with metadata",
        description = "Converts an invoice batch into the flat file core format with additional metadata, returned as JSON. Useful for integration with systems that need both the flat file content and associated metadata."
    )
    @PostMapping("/flatcore", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun makeFlatFileCore(
        @RequestBody batch: InvoicesBatch
                    ) =
        efactService.makeFlatFileCoreWithMetadata(
            batch = batch,
            isTest = false,
            isMediprima = false
                                 )

    @Operation(
        summary = "Generate a test flat file from an invoice batch",
        description = "Converts an invoice batch into the flat file format in test mode, without sending it. The test flag is set so the generated file can be used for validation purposes."
    )
    @PostMapping("/flat/test", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun makeFlatFileTest(
        @RequestBody batch: InvoicesBatch
                        ) =
        efactService.makeFlatFile(
            batch = batch,
            isTest = true,
            isMediprima = false
                                 )

    @Operation(
        summary = "Load eFact messages",
        description = "Retrieves pending electronic invoicing messages (responses, acknowledgements, errors) from the MyCareNet platform for the specified healthcare provider."
    )
    @GetMapping("/{nihii}/{language}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(
        @PathVariable nihii: String,
        @PathVariable language: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestParam limit: Int?
                    ) =
        efactService.loadMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpSsin = ssin,
            hcpNihii = nihii,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            language = language,
            limit = limit ?: Integer.MAX_VALUE
                                 )


    @Operation(
        summary = "Load Mediprima eFact messages",
        description = "Retrieves pending Mediprima electronic invoicing messages from the MyCareNet platform. Mediprima is the system used for invoicing healthcare provided to people without insurance coverage."
    )
    @GetMapping("/mediprima/{nihii}/{language}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMediprimaMessages(
        @PathVariable nihii: String,
        @PathVariable language: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestParam limit: Int?
    ) =
        efactService.loadMediprimaMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpSsin = ssin,
            hcpNihii = nihii,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            language = language,
            limit = limit ?: Integer.MAX_VALUE
        )


    @Operation(
        summary = "Confirm eFact acknowledgements",
        description = "Confirms the receipt of acknowledgement messages from the MyCareNet platform, identified by their value hashes. This prevents the same acknowledgements from being returned in subsequent load calls."
    )
    @PutMapping("/confirm/acks/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmAcks(
        @PathVariable nihii: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestBody valueHashes: List<String>
               ) =
        efactService.confirmAcks(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = nihii,
            hcpSsin = ssin,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            valueHashes = valueHashes
        )

    @Operation(
        summary = "Confirm Mediprima eFact acknowledgements",
        description = "Confirms the receipt of Mediprima acknowledgement messages from the MyCareNet platform, identified by their value hashes. This prevents the same acknowledgements from being returned in subsequent load calls."
    )
    @PutMapping("/mediprima/confirm/acks/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmMediprimaAcks(
        @PathVariable nihii: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestBody valueHashes: List<String>
    ) =
        efactService.confirmMediprimaAcks(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = nihii,
            hcpSsin = ssin,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            valueHashes = valueHashes
        )

    @Operation(
        summary = "Confirm eFact messages",
        description = "Confirms the receipt of invoicing response messages from the MyCareNet platform, identified by their value hashes. This prevents the same messages from being returned in subsequent load calls."
    )
    @PutMapping("/confirm/msgs/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmMessages(
        @PathVariable nihii: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestBody valueHashes: List<String>
    ) =
        efactService.confirmMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = nihii,
            hcpSsin = ssin,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            valueHashes = valueHashes
        )

    @Operation(
        summary = "Confirm Mediprima eFact messages",
        description = "Confirms the receipt of Mediprima invoicing response messages from the MyCareNet platform, identified by their value hashes. This prevents the same messages from being returned in subsequent load calls."
    )
    @PutMapping("/mediprima/confirm/msgs/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmMediprimaMessages(
        @PathVariable nihii: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestBody valueHashes: List<String>
    ) =
        efactService.confirmMediprimaMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = nihii,
            hcpSsin = ssin,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            valueHashes = valueHashes
        )
}
