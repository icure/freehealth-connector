package org.taktik.freehealth.middleware.web.controllers

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.AgreementService
import org.taktik.freehealth.middleware.service.impl.AgreementServiceImpl
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/agreement")
class AgreementController(val agreementService: AgreementService) {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody
    fun handleUnauthorizedRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(javax.xml.ws.soap.SOAPFaultException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: javax.xml.ws.soap.SOAPFaultException): String =
        ex.message ?: "unknown reason"

    @PostMapping("/askAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun askAgreement(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam requestType: AgreementServiceImpl.RequestTypeEnum,
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam pathologyStartDate: DateTime,
        @RequestParam pathologyCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) annex1: String?,
        @RequestParam(required = false) annex2: String?,
        @RequestParam(required = false) parameterNames: Array<String>?,
        @RequestParam(required = false) agreementStartDate: DateTime?,
        @RequestParam(required = false) agreementEndDate: DateTime?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForAnnex1: Float?,
        @RequestParam(required = false) numberOfSessionForAnnex2: Float?
    ): Any? {
        return agreementService.consultSynchronousAgreement(
            keystoreId,
            tokenId,
            passPhrase,
            requestType,
            messageEventSystem,
            messageEventCode,
            patientFirstName,
            patientLastName,
            patientGender,
            patientSsin,
            patientIo,
            patientIoMembership,
            pathologyStartDate,
            pathologyCode,
            insuranceRef,
            hcpNihii,
            hcpSsin,
            hcpFirstName,
            hcpLastName,
            orgNihii,
            organizationType,
            annex1,
            annex2,
            parameterNames,
            agreementStartDate,
            agreementEndDate,
            agreementType,
            numberOfSessionForAnnex1,
            numberOfSessionForAnnex2
        )
    }
}
