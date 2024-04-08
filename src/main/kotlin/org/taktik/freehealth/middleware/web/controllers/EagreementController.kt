package org.taktik.freehealth.middleware.web.controllers

import ma.glasnost.orika.MapperFacade
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
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EagreementService
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/eagreement")
class EagreementController(val eagreementService: EagreementService, val mapper: MapperFacade) {
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
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam requestType: String,
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam pathologyStartDate: Int,
        @RequestParam pathologyCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) annex1: String?,
        @RequestParam(required = false) annex2: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForAnnex1: Float?,
        @RequestParam(required = false) numberOfSessionForAnnex2: Float?
    ): AgreementResponse? {
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.ASK,
            hcpQuality = hcpQuality,
            messageEventSystem = "https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/message-events",
            messageEventCode = "claim-ask",
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            pathologyStartDate = formatter.parseDateTime(pathologyStartDate.toString()),
            pathologyCode = pathologyCode,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            annex1 = annex1,
            annex2 = annex2,
            agreementStartDate = formatter.parseDateTime(agreementStartDate.toString()),
            agreementEndDate = formatter.parseDateTime(agreementEndDate.toString()),
            agreementType = agreementType,
            numberOfSessionForAnnex1 = numberOfSessionForAnnex1,
            numberOfSessionForAnnex2 = numberOfSessionForAnnex2
        )
    }

    @PostMapping("/consultList", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultList(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam requestType: String,
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam pathologyStartDate: Int,
        @RequestParam pathologyCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) annex1: String?,
        @RequestParam(required = false) annex2: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForAnnex1: Float?,
        @RequestParam(required = false) numberOfSessionForAnnex2: Float?
    ): AgreementResponse? {
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST,
            hcpQuality = hcpQuality,
            messageEventSystem = "http://hl7.org/fhir/restful-interaction",
            messageEventCode = EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST.requestType,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            pathologyStartDate = formatter.parseDateTime(pathologyStartDate.toString()),
            pathologyCode = pathologyCode,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            annex1 = annex1,
            annex2 = annex2,
            agreementStartDate = formatter.parseDateTime(agreementStartDate.toString()),
            agreementEndDate = formatter.parseDateTime(agreementEndDate.toString()),
            agreementType = agreementType,
            numberOfSessionForAnnex1 = numberOfSessionForAnnex1,
            numberOfSessionForAnnex2 = numberOfSessionForAnnex2
        )
    }

    @PostMapping("/cancelAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun cancelAgreement(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam requestType: String,
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam pathologyStartDate: Int,
        @RequestParam pathologyCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) annex1: String?,
        @RequestParam(required = false) annex2: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForAnnex1: Float?,
        @RequestParam(required = false) numberOfSessionForAnnex2: Float?
    ): AgreementResponse? {
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.ASK,
            hcpQuality = hcpQuality,
            messageEventSystem = "https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/message-events",
            messageEventCode = "claim-cancel",
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            pathologyStartDate = formatter.parseDateTime(pathologyStartDate.toString()),
            pathologyCode = pathologyCode,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            annex1 = annex1,
            annex2 = annex2,
            agreementStartDate = formatter.parseDateTime(agreementStartDate.toString()),
            agreementEndDate = formatter.parseDateTime(agreementEndDate.toString()),
            agreementType = agreementType,
            numberOfSessionForAnnex1 = numberOfSessionForAnnex1,
            numberOfSessionForAnnex2 = numberOfSessionForAnnex2
        )
    }
}
