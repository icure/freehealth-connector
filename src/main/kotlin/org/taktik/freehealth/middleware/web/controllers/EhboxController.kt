/*
 *
 * Copyright (C) 2018 iCure SA
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
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.ehbox.AltKeystoresList
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo
import org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage
import org.taktik.freehealth.middleware.dto.ehbox.ErrorMessage
import org.taktik.freehealth.middleware.dto.ehbox.Message
import org.taktik.freehealth.middleware.service.EhboxService
import java.util.*

@RestController
@RequestMapping("/ehbox")
@Tag(name = "Ehbox", description = "Belgian eHealth Box secure messaging services for sending, receiving, moving, and deleting messages in the eHealth Box system.")
class EhboxController(val ehboxService: EhboxService) {
    @Operation(
        summary = "Get eHealth Box information",
        description = "Retrieves information about the authenticated user's eHealth Box, including box identifiers and configuration details."
    )
    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getInfos(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String): BoxInfo =
        ehboxService.getInfos(keystoreId, tokenId, passPhrase)

    @Operation(
        summary = "Load messages from a box",
        description = "Loads message headers from the specified eHealth Box (e.g., INBOX, SENTBOX, BININBOX). Returns a list of messages with optional limit on the number of results."
    )
    @GetMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @RequestParam limit: Int?): List<Message> =
        ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit).messages

    @Operation(
        summary = "Get a full message by ID",
        description = "Retrieves the full content of a specific message from the specified eHealth Box, including documents and attachments. The message is identified by its box ID and message ID."
    )
    @GetMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @PathVariable messageId: String): Message =
        ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId).let {it.message ?: ErrorMessage(it.error?.descr) }

    @Operation(
        summary = "Load messages from a box with alternate keystores",
        description = "Loads message headers from the specified eHealth Box using alternate keystores for decryption. This is useful when messages were encrypted with different certificates. Returns a list of messages with optional limit."
    )
    @PostMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @RequestParam limit: Int?, @RequestBody alternateKeystores: AltKeystoresList): List<Message> =
        ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, alternateKeystores.keystores).messages

    @Operation(
        summary = "Get a full message by ID with alternate keystores",
        description = "Retrieves the full content of a specific message from the specified eHealth Box using alternate keystores for decryption. This is useful when the message was encrypted with a different certificate than the current one."
    )
    @PostMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @PathVariable messageId: String, @RequestBody alternateKeystores: AltKeystoresList): Message =
        ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId, alternateKeystores.keystores).let {it.message ?: ErrorMessage(it.error?.descr) }

    @Operation(
        summary = "Send a message via eHealth Box",
        description = "Sends a document message through the Belgian eHealth Box secure messaging system. The message can include documents, free text, and attachments. Optional receipt flags control whether publication, reception, and read receipts are requested."
    )
    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody message: DocumentMessage,
        @RequestParam publicationReceipt: Boolean?,
        @RequestParam receptionReceipt: Boolean?,
        @RequestParam readReceipt: Boolean?
    ): Boolean = ehboxService.sendMessage(
        keystoreId,
        tokenId,
        passPhrase,
        message,
        publicationReceipt ?: false,
        receptionReceipt ?: false,
        readReceipt ?: false
    ).success

    @Operation(
        summary = "Move messages between boxes",
        description = "Moves one or more messages from a source eHealth Box to a destination box (e.g., from INBOX to BININBOX). The message IDs to move are provided in the request body."
    )
    @PostMapping("/move/from/{source}/to/{destination}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun moveMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String, @PathVariable destination: String): Boolean =
        ehboxService.moveMessages(keystoreId, tokenId, passPhrase, messageIds, source, destination).success

    @Operation(
        summary = "Delete messages from a box",
        description = "Deletes one or more messages from the specified source eHealth Box. The message IDs to delete are provided in the request body. This operation is permanent and cannot be undone."
    )
    @PostMapping("/move/from/{source}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun deleteMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String): Boolean =
        ehboxService.deleteMessages(keystoreId, tokenId, passPhrase, messageIds, source).success
}
