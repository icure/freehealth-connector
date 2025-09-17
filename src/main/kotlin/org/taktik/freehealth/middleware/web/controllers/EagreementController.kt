package org.taktik.freehealth.middleware.web.controllers

import ma.glasnost.orika.MapperFacade
import org.joda.time.format.DateTimeFormat
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.taktik.connector.business.agreement.domain.Agreement
import org.taktik.connector.business.agreement.domain.AgreementMessage
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.connector.business.domain.agreement.EAgreementResponse
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EagreementService
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.xml.ws.soap.SOAPFaultException

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
    @ExceptionHandler(SOAPFaultException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: SOAPFaultException): String =
        ex.message ?: "unknown reason"

    data class Attachment(
        val type: String,
        val data: String
    )

    @PostMapping("/askAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun askAgreement(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam prescriberNihii: String,
        @RequestParam prescriberFirstName: String,
        @RequestParam prescriberLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam pathologyStartDate: Int,
        @RequestParam pathologyCode: String,
        @RequestParam sctCode: String,
        @RequestParam(required = false) sctDisplay: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForPrescription1: Float?,
        @RequestParam(required = false) numberOfSessionForPrescription2: Float?,
        @RequestBody(required = false) attachments: List<Attachment>?
    ): EAgreementResponse? {
        val formatter = DateTimeFormat.forPattern("yyyyMMdd")
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
            insuranceRef = null,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            prescriberNihii = prescriberNihii,
            prescriberFirstName = prescriberFirstName,
            prescriberLastName = prescriberLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            prescription1 = attachments?.find { it.type == "prescription1" }?.data,
            prescription2 = attachments?.find { it.type == "prescription2" }?.data,
            agreementStartDate = if (agreementStartDate != null ) formatter.parseDateTime(agreementStartDate.toString()) else null,
            agreementEndDate = if (agreementEndDate != null) formatter.parseDateTime(agreementEndDate.toString()) else null,
            agreementType = agreementType,
            numberOfSessionForPrescription1 = numberOfSessionForPrescription1,
            numberOfSessionForPrescription2 = numberOfSessionForPrescription2,
            sctCode = sctCode,
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    @PostMapping("/sendAsk", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendAsk(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody agreement: Agreement
    ): AgreementResponse? {
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            agreement = agreement
        )
    }

    @PostMapping("/sendConsult", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendConsult(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody agreement: Agreement
    ): AgreementResponse? {
        return eagreementService.consultAgreementList(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            agreement = agreement
        )
    }

    @GetMapping("/getMessages/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAgreementMessages(
        @PathVariable nihii: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestParam limit: Int?
    ): List<AgreementMessage> =
        eagreementService.getEAgreementMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpSsin = ssin,
            hcpNihii = nihii,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            limit = limit ?: Integer.MAX_VALUE
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
        @RequestBody references: List<String>
    ) =
        eagreementService.confirmMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = nihii,
            hcpSsin = ssin,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            references = references
        )

    @PostMapping("/consultList", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultList(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
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
        val formatter = DateTimeFormat.forPattern("yyyyMMdd")
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
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementType: String?
    ): EAgreementResponse? {
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
            pathologyStartDate = null,
            pathologyCode = null,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            prescriberNihii = null,
            prescriberFirstName = null,
            prescriberLastName = null,
            orgNihii = orgNihii,
            organizationType = organizationType,
            prescription1 = null,
            prescription2 = null,
            agreementStartDate = null,
            agreementEndDate = null,
            agreementType = agreementType,
            numberOfSessionForPrescription1 = null,
            numberOfSessionForPrescription2 = null,
            sctCode = null,
            sctDisplay = null,
            attachments = null
        )
    }

    @PostMapping("/extendAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun extendAgreement(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam prescriberNihii: String,
        @RequestParam prescriberFirstName: String,
        @RequestParam prescriberLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam pathologyStartDate: Int,
        @RequestParam pathologyCode: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) sctCode: String?,
        @RequestParam(required = false) sctDisplay: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForPrescription1: Float?,
        @RequestParam(required = false) numberOfSessionForPrescription2: Float?,
        @RequestBody(required = false) attachments: List<Attachment>?
    ): EAgreementResponse? {
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
            prescriberNihii = prescriberNihii,
            prescriberFirstName = prescriberFirstName,
            prescriberLastName = prescriberLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            prescription1 = attachments?.find { it.type == "prescription1" }?.data,
            prescription2 = attachments?.find { it.type == "prescription2" }?.data,
            agreementStartDate = if (agreementStartDate != null ) formatter.parseDateTime(agreementStartDate.toString()) else null,
            agreementEndDate = if (agreementEndDate != null) formatter.parseDateTime(agreementEndDate.toString()) else null,
            agreementType = agreementType,
            numberOfSessionForPrescription1 = numberOfSessionForPrescription1,
            numberOfSessionForPrescription2 = numberOfSessionForPrescription2,
            sctCode = sctCode,
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    @PostMapping("/completeAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun completeAgreement(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam prescriberNihii: String,
        @RequestParam prescriberFirstName: String,
        @RequestParam prescriberLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) sctCode: String?,
        @RequestParam(required = false) sctDisplay: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForPrescription1: Float?,
        @RequestParam(required = false) numberOfSessionForPrescription2: Float?,
        @RequestBody(required = false) attachments: List<Attachment>?
    ): EAgreementResponse? {
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
            prescriberNihii = prescriberNihii,
            prescriberFirstName = prescriberFirstName,
            prescriberLastName = prescriberLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            prescription1 = attachments?.find { it.type == "prescription1" }?.data,
            prescription2 = attachments?.find { it.type == "prescription2" }?.data,
            agreementStartDate = null,
            agreementEndDate = null,
            agreementType = agreementType,
            numberOfSessionForPrescription1 = numberOfSessionForPrescription1,
            numberOfSessionForPrescription2 = numberOfSessionForPrescription2,
            sctCode = sctCode,
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    @PostMapping("/argueAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun argueAgreement(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam prescriberNihii: String,
        @RequestParam prescriberFirstName: String,
        @RequestParam prescriberLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam insuranceRef: String,
        @RequestParam(required = false) sctCode: String?,
        @RequestParam(required = false) sctDisplay: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForPrescription1: Float?,
        @RequestParam(required = false) numberOfSessionForPrescription2: Float?,
        @RequestBody(required = false) attachments: List<Attachment>?
    ): EAgreementResponse? {
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
            prescriberNihii = prescriberNihii,
            prescriberFirstName = prescriberFirstName,
            prescriberLastName = prescriberLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            prescription1 = attachments?.find { it.type == "prescription1" }?.data,
            prescription2 = attachments?.find { it.type == "prescription2" }?.data,
            agreementStartDate = null,
            agreementEndDate = null,
            agreementType = agreementType,
            numberOfSessionForPrescription1 = numberOfSessionForPrescription1,
            numberOfSessionForPrescription2 = numberOfSessionForPrescription2,
            sctCode = sctCode,
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }
}
