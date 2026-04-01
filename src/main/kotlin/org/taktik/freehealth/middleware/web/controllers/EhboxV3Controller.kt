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
import org.taktik.freehealth.middleware.dto.ehbox.MessageOperationResponse
import org.taktik.freehealth.middleware.dto.ehbox.MessageResponse
import org.taktik.freehealth.middleware.dto.ehbox.MessagesResponse
import org.taktik.freehealth.middleware.service.EhboxService
import java.util.*

@RestController
@RequestMapping("/ehboxV3")
@Tag(name = "EhboxV3", description = "Belgian eHealth Box V3 secure messaging services for sending, receiving, moving, and deleting messages using the V3 API, with extended response metadata.")
class EhboxV3Controller(val ehboxService: EhboxService) {
    @Operation(
        summary = "Get eHealth Box V3 information",
        description = "Retrieves information about the authenticated user's eHealth Box using the V3 API, including box identifiers and configuration details."
    )
    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getInfos(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String): BoxInfo =
        ehboxService.getInfos(keystoreId, tokenId, passPhrase)

    @Operation(
        summary = "Load messages from a box",
        description = "Loads messages from the specified eHealth Box (e.g., INBOX, SENTBOX, BININBOX) using the V3 API. Returns a MessagesResponse containing the message list and any error information, with optional limit on the number of results."
    )
    @GetMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @RequestParam limit: Int?): MessagesResponse =
        ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit)

    @Operation(
        summary = "Get a full message by ID",
        description = "Retrieves the full content of a specific message from the specified eHealth Box using the V3 API, including documents and attachments. Returns a MessageResponse containing the message and any error information."
    )
    @GetMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @PathVariable messageId: String): MessageResponse =
        ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId)

    @Operation(
        summary = "Load messages from a box with alternate keystores",
        description = "Loads messages from the specified eHealth Box using alternate keystores for decryption via the V3 API. This is useful when messages were encrypted with different certificates. Returns a MessagesResponse with message list and error information."
    )
    @PostMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @RequestParam limit: Int?, @RequestBody alternateKeystores: AltKeystoresList): MessagesResponse =
        ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, alternateKeystores.keystores)

    @Operation(
        summary = "Get a full message by ID with alternate keystores",
        description = "Retrieves the full content of a specific message from the specified eHealth Box using alternate keystores for decryption via the V3 API. This is useful when the message was encrypted with a different certificate than the current one."
    )
    @PostMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @PathVariable messageId: String, @RequestBody alternateKeystores: AltKeystoresList): MessageResponse =
        ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId, alternateKeystores.keystores)

    @Operation(
        summary = "Send a message via eHealth Box V3",
        description = "Sends a document message through the Belgian eHealth Box V3 secure messaging system. The message can include documents, free text, and attachments. Optional receipt flags control whether publication, reception, and read receipts are requested. Returns a MessageOperationResponse with success status."
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
    ): MessageOperationResponse = ehboxService.sendMessage(
        keystoreId,
        tokenId,
        passPhrase,
        message,
        publicationReceipt ?: false,
        receptionReceipt ?: false,
        readReceipt ?: false
                                                          )

    @Operation(
        summary = "Get message acknowledgment status",
        description = "Retrieves the acknowledgment status for a specific message, including publication, reception, and read receipts. This allows the sender to verify whether the message has been delivered and read by the recipient."
    )
    @GetMapping("/{messageId}/status/acks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMessageAckStatus(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable messageId: String
        )  = ehboxService.getMessageAckStatus(keystoreId, tokenId, passPhrase, messageId)

    @Operation(
        summary = "Send a message to a secondary eHealth Box",
        description = "Sends a document message to a secondary eHealth Box (2ebox) through the V3 API. This is used for routing messages to an alternate eHealth Box destination. Optional receipt flags control whether publication, reception, and read receipts are requested."
    )
    @PostMapping("/2ebox", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMessage2Ebox(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody message: DocumentMessage,
        @RequestParam publicationReceipt: Boolean?,
        @RequestParam receptionReceipt: Boolean?,
        @RequestParam readReceipt: Boolean?
                   ): MessageOperationResponse = ehboxService.sendMessage2Ebox(
        keystoreId,
        tokenId,
        passPhrase,
        message,
        publicationReceipt ?: false,
        receptionReceipt ?: false,
        readReceipt ?: false
                                                                         )

    @Operation(
        summary = "Move messages between boxes",
        description = "Moves one or more messages from a source eHealth Box to a destination box (e.g., from INBOX to BININBOX) using the V3 API. The message IDs to move are provided in the request body. Returns a MessageOperationResponse with success status."
    )
    @PostMapping("/move/from/{source}/to/{destination}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun moveMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String, @PathVariable destination: String): MessageOperationResponse =
        ehboxService.moveMessages(keystoreId, tokenId, passPhrase, messageIds, source, destination)

    @Operation(
        summary = "Delete messages from a box",
        description = "Deletes one or more messages from the specified source eHealth Box using the V3 API. The message IDs to delete are provided in the request body. Returns a MessageOperationResponse with success status. This operation is permanent and cannot be undone."
    )
    @PostMapping("/move/from/{source}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun deleteMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String): MessageOperationResponse =
        ehboxService.deleteMessages(keystoreId, tokenId, passPhrase, messageIds, source)
}
