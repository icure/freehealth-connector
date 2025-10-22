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
import org.taktik.freehealth.utils.LoggingMdcUtil
import java.util.*

@RestController
@RequestMapping("/ehboxV3")
class EhboxV3Controller(val ehboxService: EhboxService) {
    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getInfos(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean
    ): BoxInfo {
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val getInfosResult = ehboxService.getInfos(keystoreId, tokenId, passPhrase)
        LoggingMdcUtil.clearMDC()
        return getInfosResult
    }

    @GetMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @PathVariable boxId: String,
        @RequestParam limit: Int?,
        @RequestParam(defaultValue = "0") skip: Int
    ): MessagesResponse{
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val loadMessagesResult = ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, skip)
        LoggingMdcUtil.clearMDC()
        return loadMessagesResult
    }


    @GetMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @PathVariable boxId: String,
        @PathVariable messageId: String
    ): MessageResponse{
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val getFullMessageResult = ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId)
        LoggingMdcUtil.clearMDC()
        return getFullMessageResult
    }

    @PostMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @PathVariable boxId: String,
        @RequestParam limit: Int?,
        @RequestParam(defaultValue = "0") skip: Int,
        @RequestBody alternateKeystores: AltKeystoresList
    ): MessagesResponse{
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val loadMessagesResult = ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, skip, alternateKeystores.keystores)
        LoggingMdcUtil.clearMDC()
        return loadMessagesResult
    }

    @PostMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @PathVariable boxId: String,
        @PathVariable messageId: String,
        @RequestBody alternateKeystores: AltKeystoresList
    ): MessageResponse{
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val getFullMessageResult = ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId, alternateKeystores.keystores)
        LoggingMdcUtil.clearMDC()
        return getFullMessageResult
    }

    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @RequestBody message: DocumentMessage,
        @RequestParam publicationReceipt: Boolean?,
        @RequestParam receptionReceipt: Boolean?,
        @RequestParam readReceipt: Boolean?
    ): MessageOperationResponse{
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
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
        LoggingMdcUtil.clearMDC()
        return getFullMessageResult;
    }

    @GetMapping("/{messageId}/status/acks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMessageAckStatus(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @PathVariable messageId: String
    ): MessageStatusOperationResponse {
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val getMessageAckStatusResult =  ehboxService.getMessageAckStatus(keystoreId, tokenId, passPhrase, messageId)
        LoggingMdcUtil.clearMDC()
        return getMessageAckStatusResult
    }

    @PostMapping("/2ebox", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMessage2Ebox(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @RequestBody message: DocumentMessage,
        @RequestParam publicationReceipt: Boolean?,
        @RequestParam receptionReceipt: Boolean?,
        @RequestParam readReceipt: Boolean?
    ): MessageOperationResponse {
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val sendMessage2EboxResult =  ehboxService.sendMessage2Ebox(
            keystoreId,
            tokenId,
            passPhrase,
            message,
            publicationReceipt ?: false,
            receptionReceipt ?: false,
            readReceipt ?: false
        )
        LoggingMdcUtil.clearMDC()
        return sendMessage2EboxResult
    }

    @PostMapping("/move/from/{source}/to/{destination}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun moveMessages(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @RequestBody messageIds: List<String>,
        @PathVariable source: String,
        @PathVariable destination: String
    ): MessageOperationResponse{
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val moveMessagesResult = ehboxService.moveMessages(keystoreId, tokenId, passPhrase, messageIds, source, destination)
        LoggingMdcUtil.clearMDC()
        return moveMessagesResult
    }

    @PostMapping("/move/from/{source}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun deleteMessages(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @RequestHeader(name = "X-FHC-debug", required = false, defaultValue = "false") debug: Boolean,
        @RequestBody messageIds: List<String>,
        @PathVariable source: String
    ): MessageOperationResponse{
        LoggingMdcUtil.setMDC(keystoreId, company, debug)
        val deleteMessagesResult = ehboxService.deleteMessages(keystoreId, tokenId, passPhrase, messageIds, source)
        LoggingMdcUtil.clearMDC()
        return deleteMessagesResult
    }
}
