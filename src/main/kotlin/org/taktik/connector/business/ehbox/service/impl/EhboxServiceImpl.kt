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

package org.taktik.connector.business.ehbox.service.impl

import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.*
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.taktik.connector.business.ehbox.service.EhboxService
import org.taktik.connector.business.ehbox.service.ServiceFactory
import org.taktik.connector.business.ehbox.v3.exception.OoOPublicationException
import org.taktik.connector.business.ehbox.v3.validator.EhboxReplyValidator
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.GenericResponse
import org.taktik.freehealth.utils.LoggingMdcUtil
import org.taktik.freehealth.utils.SOAPMessageConverter.toXmlString

class EhboxServiceImpl(val replyValidator: EhboxReplyValidator) : EhboxService {
    val log: Logger = LoggerFactory.getLogger(EhboxServiceImpl::class.java)

    @Throws(ConnectorException::class)
    private fun <T : ResponseType> callConsultationService(
        token: SAMLToken,
        request: Any,
        soapAction: String,
        clazz: Class<T>
    ): T {
        try {
            val service = ServiceFactory.getConsultationService(token)
            service.setPayload(request)
            service.setSoapAction(soapAction)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            debugLog(soapAction,  service, xmlResponse)
            val response = xmlResponse.asObject(clazz) as T
            this.replyValidator.validateReplyStatus(response)
            return response
        } catch (exception: Exception) {
            if (exception is TechnicalConnectorException) {
                throw exception
            } else {
                throw TechnicalConnectorException(
                    TechnicalConnectorExceptionValues.ERROR_WS,
                    exception,
                    exception.message
                )
            }
        }
    }

    @Throws(ConnectorException::class)
    override fun getBoxInfo(token: SAMLToken, getBoxInfoRequest: GetBoxInfoRequest): GetBoxInfoResponse {
        return this.callConsultationService(
            token,
            getBoxInfoRequest,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getBoxInfo",
            GetBoxInfoResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun getFullMessage(token: SAMLToken, request: GetFullMessageRequest): GetFullMessageResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getFullMessage",
            GetFullMessageResponse::class.java
        )
    }

    @Deprecated("")
    @Throws(ConnectorException::class)
    override fun getFullMessage(token: SAMLToken, request: MessageRequestType): GetFullMessageResponse {
        return this.callConsultationService(
            token,
            this.mapToCorrectType(request, GetFullMessageRequest()),
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getFullMessage",
            GetFullMessageResponse::class.java
        )
    }

    @Deprecated("")
    @Throws(ConnectorException::class)
    override fun getMessageHistory(token: SAMLToken, request: MessageRequestType): GetHistoryResponse {
        return this.callConsultationService(
            token,
            this.mapToCorrectType(request, GetHistoryRequest()),
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessageHistory",
            GetHistoryResponse::class.java
        )
    }

    private fun <T : MessageRequestType> mapToCorrectType(
        originalRequest: MessageRequestType,
        newInstanceOfCorrectType: T
    ): T {
        newInstanceOfCorrectType.boxId = originalRequest.boxId
        newInstanceOfCorrectType.messageId = originalRequest.messageId
        newInstanceOfCorrectType.source = originalRequest.source
        return newInstanceOfCorrectType
    }

    @Throws(ConnectorException::class)
    override fun getMessageHistory(token: SAMLToken, request: GetHistoryRequest): GetHistoryResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessageHistory",
            GetHistoryResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun getMessageList(token: SAMLToken, request: GetMessagesListRequest): GetMessagesListResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessagesList",
            GetMessagesListResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun getMessageAcknowledgmentsStatusResponse(
        token: SAMLToken,
        request: GetMessageAcknowledgmentsStatusRequest
    ): GetMessageAcknowledgmentsStatusResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessageAcknowledgmentsStatus",
            GetMessageAcknowledgmentsStatusResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun deleteMessage(token: SAMLToken, request: DeleteMessageRequest): DeleteMessageResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:deleteMessage",
            DeleteMessageResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun moveMessage(token: SAMLToken, request: MoveMessageRequest): MoveMessageResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:moveMessage",
            MoveMessageResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun insertOoO(token: SAMLToken, request: InsertOoORequest): InsertOoOResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:insertOoO",
            InsertOoOResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun deleteOoO(token: SAMLToken, request: DeleteOoORequest): DeleteOoOResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:deleteOoO",
            DeleteOoOResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun getOoOList(token: SAMLToken, request: GetOoOListRequest): GetOoOListResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getOoOList",
            GetOoOListResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun getAllEhboxesMessagesList(
        token: SAMLToken,
        request: GetAllEhboxesMessagesListRequest
    ): GetAllEhboxesMessagesListResponse {
        return this.callConsultationService(
            token,
            request,
            "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getAllEhboxesMessagesList",
            GetAllEhboxesMessagesListResponse::class.java
        )
    }

    @Throws(ConnectorException::class)
    override fun sendMessage(token: SAMLToken, request: SendMessageRequest): SendMessageResponse {
        try {
            val soapAction = "urn:be:fgov:ehealth:ehbox:publication:protocol:v3:sendMessage"
            val service = ServiceFactory.getPublicationService(token)
            service.setPayload(request as Any)
            service.setSoapAction(soapAction)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val response = xmlResponse.asObject(SendMessageResponse::class.java) as SendMessageResponse
            debugLog(soapAction,  service, xmlResponse)
            this.replyValidator.validateReplyStatus(response)
            return response
        } catch (ex: Exception) {
            if (ex !is OoOPublicationException && ex !is TechnicalConnectorException) {
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
            } else {
                throw ex as ConnectorException
            }
        }
    }

    @Throws(ConnectorException::class)
    override fun sendMessage2Ebox(token: SAMLToken, request: SendMessageRequest): SendMessageResponse {
        try {
            val soapAction = "urn:be:fgov:ehealth:ehbox:publication:protocol:v3:sendMessage"
            val service = ServiceFactory.getEh2EboxPublicationService(token)
            service.setPayload(request as Any)
            service.setSoapAction(soapAction)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            debugLog(soapAction,  service, xmlResponse)
            val response = xmlResponse.asObject(SendMessageResponse::class.java) as SendMessageResponse
            this.replyValidator.validateReplyStatus(response)
            return response
        } catch (ex: Exception) {
            if (ex !is OoOPublicationException && ex !is TechnicalConnectorException) {
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
            } else {
                throw ex as ConnectorException
            }
        }
    }

    private fun debugLog(
        soapAction: String,
        xmlRequest: GenericRequest?,
        xmlResponse: GenericResponse?
    ) {
        val debug = LoggingMdcUtil.getBooleanMDC("debug")
        if (debug) {
            val action = soapAction
                .replace("urn:be:fgov:ehealth:ehbox:publication:protocol:v3:", "")
                .replace("urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:", "")

            if(xmlResponse != null &&  xmlRequest != null){
                log.warn("{} Request: [{}]", action, xmlRequest.payload.toXmlString())
                log.warn("{} Response: [{}]", action, xmlResponse.soapMessage.toXmlString())
            }else{
                log.warn("{} Request: [{}]", action, "No data")
                log.warn("{} Response: [{}]", action, "No data")
            }
        }
    }

}
