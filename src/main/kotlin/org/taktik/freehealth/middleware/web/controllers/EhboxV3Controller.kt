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

import org.apache.log4j.MDC
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.ehbox.*
import org.taktik.freehealth.middleware.service.EhboxService
import java.util.*

@RestController
@RequestMapping("/ehboxV3")
class EhboxV3Controller(val ehboxService: EhboxService) {
    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getInfos(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false) company: String): BoxInfo
    {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val getInfosResult = ehboxService.getInfos(keystoreId, tokenId, passPhrase)
        MDC.clear()
        return getInfosResult
    }

    @GetMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false) company: String, @PathVariable boxId: String, @RequestParam limit: Int?, @RequestParam(defaultValue = "0") skip: Int): MessagesResponse{
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val loadMessagesResult = ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, skip)
        MDC.clear()
        return loadMessagesResult
    }


    @GetMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false) company: String, @PathVariable boxId: String, @PathVariable messageId: String): MessageResponse{
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val getFullMessageResult = ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId)
        MDC.clear()
        return getFullMessageResult
    }

    @PostMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false) company: String, @PathVariable boxId: String, @RequestParam limit: Int?, @RequestParam(defaultValue = "0") skip: Int, @RequestBody alternateKeystores: AltKeystoresList): MessagesResponse{
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val loadMessagesResult = ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, skip, alternateKeystores.keystores)
        MDC.clear()
        return loadMessagesResult
    }

    @PostMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false) company: String, @PathVariable boxId: String, @PathVariable messageId: String, @RequestBody alternateKeystores: AltKeystoresList): MessageResponse
    {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val getFullMessageResult = ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId, alternateKeystores.keystores)
        MDC.clear()
        return getFullMessageResult
    }

    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false) company: String,
        @RequestBody message: DocumentMessage,
        @RequestParam publicationReceipt: Boolean?,
        @RequestParam receptionReceipt: Boolean?,
        @RequestParam readReceipt: Boolean?
    ): MessageOperationResponse{
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val getFullMessageResult =
            ehboxService.sendMessage(
                keystoreId,
                tokenId,
                passPhrase,
                message,
                publicationReceipt ?: false,
                receptionReceipt ?: false,
                readReceipt ?: false
            )
        MDC.clear()
        return getFullMessageResult;
    }

    @GetMapping("/{messageId}/status/acks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMessageAckStatus(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false) company: String,
        @PathVariable messageId: String
        ): MessageStatusOperationResponse {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val getMessageAckStatusResult =  ehboxService.getMessageAckStatus(keystoreId, tokenId, passPhrase, messageId)
        MDC.clear()
        return getMessageAckStatusResult
    }

    @PostMapping("/2ebox", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMessage2Ebox(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false) company: String,
        @RequestBody message: DocumentMessage,
        @RequestParam publicationReceipt: Boolean?,
        @RequestParam receptionReceipt: Boolean?,
        @RequestParam readReceipt: Boolean?
                   ): MessageOperationResponse {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val sendMessage2EboxResult =  ehboxService.sendMessage2Ebox(
            keystoreId,
            tokenId,
            passPhrase,
            message,
            publicationReceipt ?: false,
            receptionReceipt ?: false,
            readReceipt ?: false
        )
        MDC.clear()
        return sendMessage2EboxResult
    }

    @PostMapping("/move/from/{source}/to/{destination}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun moveMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false) company: String, @RequestBody messageIds: List<String>, @PathVariable source: String, @PathVariable destination: String): MessageOperationResponse{
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val moveMessagesResult = ehboxService.moveMessages(keystoreId, tokenId, passPhrase, messageIds, source, destination)
        MDC.clear()
        return moveMessagesResult
    }

    @PostMapping("/move/from/{source}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun deleteMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false) company: String, @RequestBody messageIds: List<String>, @PathVariable source: String): MessageOperationResponse{
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val deleteMessagesResult = ehboxService.deleteMessages(keystoreId, tokenId, passPhrase, messageIds, source)
        MDC.clear()
        return deleteMessagesResult
    }
}
