package org.taktik.freehealth.middleware.web.controllers

import ma.glasnost.orika.MapperFacade
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
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam pathologyStartDate: Int,
        @RequestParam pathologyCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam sctCode: String,
        @RequestParam(required = false) sctDisplay: String?,
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
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.MESSAGE_EVENTS,
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
            numberOfSessionForAnnex2 = numberOfSessionForAnnex2,
            sctCode = sctCode,
            sctDisplay = sctDisplay
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
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam subTypeCode: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?
    ): AgreementResponse? {
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
        return eagreementService.consultAgreementList(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST,
            hcpQuality = hcpQuality,
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.INTERACTION,
            messageEventCode = EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST.requestType,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            subTypeCode = subTypeCode,
            orgNihii = orgNihii,
            organizationType = organizationType,
            agreementStartDate = formatter.parseDateTime(agreementStartDate.toString()),
            agreementEndDate = formatter.parseDateTime(agreementEndDate.toString()),
            agreementType = agreementType
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
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementType: String?
    ): AgreementResponse? {
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.CANCEL,
            hcpQuality = hcpQuality,
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.MESSAGE_EVENTS,
            messageEventCode = "claim-cancel",
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            agreementType = agreementType,
            agreementEndDate = null,
            agreementStartDate = null,
            annex1 = null,
            annex2 = null,
            pathologyCode = null,
            pathologyStartDate = null,
            numberOfSessionForAnnex1 = null,
            numberOfSessionForAnnex2 = null,
            sctCode = null,
            sctDisplay = null
        )
    }

    @PostMapping("/extendAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun extendAgreement(
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
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam pathologyStartDate: Int,
        @RequestParam pathologyCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam sctCode: String,
        @RequestParam(required = false) sctDisplay: String?,
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
            requestType = EagreementServiceImpl.RequestTypeEnum.EXTEND,
            hcpQuality = hcpQuality,
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.MESSAGE_EVENTS,
            messageEventCode = "claim-extend",
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
            numberOfSessionForAnnex2 = numberOfSessionForAnnex2,
            sctCode = sctCode,
            sctDisplay = sctDisplay
        )
    }

    @PostMapping("/completeAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun completeAgreement(
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
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam sctCode: String,
        @RequestParam(required = false) sctDisplay: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) annex1: String?,
        @RequestParam(required = false) annex2: String?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForAnnex1: Float?,
        @RequestParam(required = false) numberOfSessionForAnnex2: Float?
    ): AgreementResponse? {
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.COMPLETE_AGREEMENT,
            hcpQuality = hcpQuality,
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.MESSAGE_EVENTS,
            messageEventCode = "claim-completeAgreement",
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            pathologyStartDate = null,
            pathologyCode = null,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            annex1 = annex1,
            annex2 = annex2,
            agreementStartDate = null,
            agreementEndDate = null,
            agreementType = agreementType,
            numberOfSessionForAnnex1 = numberOfSessionForAnnex1,
            numberOfSessionForAnnex2 = numberOfSessionForAnnex2,
            sctCode = sctCode,
            sctDisplay = sctDisplay
        )
    }

    @PostMapping("/argueAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun argueAgreement(
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
        @RequestParam messageEventSystem: String,
        @RequestParam messageEventCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam sctCode: String,
        @RequestParam(required = false) sctDisplay: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) annex1: String?,
        @RequestParam(required = false) annex2: String?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForAnnex1: Float?,
        @RequestParam(required = false) numberOfSessionForAnnex2: Float?
    ): AgreementResponse? {
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.ARGUE,
            hcpQuality = hcpQuality,
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.MESSAGE_EVENTS,
            messageEventCode = "claim-argue",
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            pathologyStartDate = null,
            pathologyCode = null,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            annex1 = annex1,
            annex2 = annex2,
            agreementStartDate = null,
            agreementEndDate = null,
            agreementType = agreementType,
            numberOfSessionForAnnex1 = numberOfSessionForAnnex1,
            numberOfSessionForAnnex2 = numberOfSessionForAnnex2,
            sctCode = sctCode,
            sctDisplay = sctDisplay
        )
    }
}
