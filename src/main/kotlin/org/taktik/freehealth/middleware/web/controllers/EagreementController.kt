package org.taktik.freehealth.middleware.web.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.domain.agreement.EAgreementResponse
import org.taktik.freehealth.middleware.domain.eAgreement.EAgreementList
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EagreementService
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl
import java.util.*
import jakarta.servlet.http.HttpServletRequest

/**
 * REST controller for Belgian electronic agreement (eAgreement) operations.
 *
 * The eAgreement service allows healthcare providers to submit, consult, cancel, extend,
 * complete, and argue prior authorization requests to insurance organizations for specific
 * treatments (e.g. physiotherapy, speech therapy). This replaces the paper-based workflow
 * with a fully electronic process via the MyCareNet platform.
 *
 * All endpoints require a valid PKCS12 keystore and SAML token, provided via HTTP headers.
 * Patient identification can be done either by SSIN or by insurance organization (IO)
 * code combined with a membership number.
 */
@RestController
@RequestMapping("/eagreement")
@Tag(name = "Eagreement", description = "Electronic agreement requests for prior authorization from insurance organizations for specific treatments.")
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
    @ExceptionHandler(jakarta.xml.ws.soap.SOAPFaultException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: jakarta.xml.ws.soap.SOAPFaultException): String =
        ex.message ?: "unknown reason"

    data class Attachment(
        val type: String,
        val data: String
    )

    /**
     * Submits a new electronic agreement request to the insurance organization for prior
     * authorization of a specific treatment. The request includes pathology details,
     * prescriber information, and optional prescription attachments.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpQuality provider quality/role (e.g. "doctor", "dentist", "physiotherapist")
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpSsin healthcare provider's SSIN
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param prescriberNihii NIHII number of the prescribing healthcare provider
     * @param prescriberFirstName prescriber's first name
     * @param prescriberLastName prescriber's last name
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param pathologyStartDate start date of the pathology in yyyyMMdd integer format
     * @param pathologyCode code identifying the pathology
     * @param sctCode SNOMED CT code for the treatment
     * @param prescriptionDate date of the prescription in yyyyMMdd integer format
     * @param sctDisplay optional SNOMED CT display text for the treatment
     * @param patientSsin optional patient's social security identification number
     * @param patientIo optional insurance organization code for the patient
     * @param patientIoMembership optional patient's membership number with the insurance organization
     * @param orgNihii optional NIHII number of the healthcare organization
     * @param organizationType optional type of the healthcare organization
     * @param agreementStartDate optional requested start date of the agreement in yyyyMMdd integer format
     * @param agreementEndDate optional requested end date of the agreement in yyyyMMdd integer format
     * @param agreementType optional type of agreement being requested
     * @param numberOfSessionForPrescription1 optional number of sessions for the first prescription
     * @param numberOfSessionForPrescription2 optional number of sessions for the second prescription
     * @param attachments optional list of attachments including prescriptions and supporting documents
     * @return the electronic agreement response from the insurance organization, or null if no response
     */
    @Operation(
        summary = "Ask for an agreement",
        description = "Submits a new electronic agreement request to the insurance organization for prior authorization of a specific treatment."
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
        @RequestParam prescriptionDate: Int,
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
            prescriptionDate = formatter.parseDateTime(prescriptionDate.toString()),
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    /**
     * Retrieves a list of existing electronic agreements for a patient, optionally filtered
     * by date range, agreement type, and insurance reference. This allows healthcare providers
     * to look up the current status and history of prior authorizations.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpQuality provider quality/role (e.g. "doctor", "dentist", "physiotherapist")
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpName healthcare provider's full name
     * @param hcpSsin healthcare provider's SSIN
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param subTypeCode sub-type code for the agreement consultation query
     * @param insuranceRef optional insurance reference to look up a specific agreement
     * @param patientSsin optional patient's social security identification number
     * @param patientIo optional insurance organization code for the patient
     * @param patientIoMembership optional patient's membership number with the insurance organization
     * @param orgNihii optional NIHII number of the healthcare organization
     * @param organizationType optional type of the healthcare organization
     * @param agreementStartDate optional start date filter for agreements in yyyyMMdd integer format
     * @param agreementEndDate optional end date filter for agreements in yyyyMMdd integer format
     * @param agreementType optional agreement type filter
     * @return the electronic agreement response containing the list of matching agreements, or null if no response
     */
    @Operation(
        summary = "Consult agreement list",
        description = "Retrieves a list of existing electronic agreements for a patient, optionally filtered by date range, agreement type, and insurance reference."
    )
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
        @RequestParam subTypeCode: String,
        @RequestParam(required = false) insuranceRef: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?
    ): EAgreementResponse? {
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
            agreementStartDate = if (agreementStartDate != null) formatter.parseDateTime(agreementStartDate.toString()) else null,
            agreementEndDate = if (agreementEndDate != null) formatter.parseDateTime(agreementEndDate.toString()) else null,
            agreementType = agreementType
        )
    }

    /**
     * Cancels a previously submitted electronic agreement request using its insurance reference.
     * This sends a cancellation message to the insurance organization via the MyCareNet platform.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpQuality provider quality/role (e.g. "doctor", "dentist", "physiotherapist")
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpName healthcare provider's full name
     * @param hcpSsin healthcare provider's SSIN
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param insuranceRef insurance reference identifying the agreement to cancel
     * @param prescriptionDate date of the prescription in yyyyMMdd integer format
     * @param patientSsin optional patient's social security identification number
     * @param patientIo optional insurance organization code for the patient
     * @param patientIoMembership optional patient's membership number with the insurance organization
     * @param orgNihii optional NIHII number of the healthcare organization
     * @param organizationType optional type of the healthcare organization
     * @param agreementType optional type of the agreement being cancelled
     * @return the electronic agreement cancellation response from the insurance organization, or null if no response
     */
    @Operation(
        summary = "Cancel an agreement",
        description = "Cancels a previously submitted electronic agreement request using its insurance reference."
    )
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
        @RequestParam insuranceRef: String,
        @RequestParam prescriptionDate: Int,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementType: String?
    ): EAgreementResponse? {
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
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
            prescriptionDate = formatter.parseDateTime(prescriptionDate.toString()),
            sctDisplay = null,
            attachments = null
        )
    }

    /**
     * Requests an extension of an existing electronic agreement, providing updated prescriptions
     * and session details. This is used when the treatment authorized by a prior agreement needs
     * to continue beyond its originally approved period.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpQuality provider quality/role (e.g. "doctor", "dentist", "physiotherapist")
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpSsin healthcare provider's SSIN
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param prescriberNihii NIHII number of the prescribing healthcare provider
     * @param prescriberFirstName prescriber's first name
     * @param prescriberLastName prescriber's last name
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param pathologyStartDate start date of the pathology in yyyyMMdd integer format
     * @param pathologyCode code identifying the pathology
     * @param insuranceRef insurance reference identifying the existing agreement to extend
     * @param prescriptionDate date of the prescription in yyyyMMdd integer format
     * @param sctCode optional SNOMED CT code for the treatment
     * @param sctDisplay optional SNOMED CT display text for the treatment
     * @param patientSsin optional patient's social security identification number
     * @param patientIo optional insurance organization code for the patient
     * @param patientIoMembership optional patient's membership number with the insurance organization
     * @param orgNihii optional NIHII number of the healthcare organization
     * @param organizationType optional type of the healthcare organization
     * @param agreementStartDate optional requested start date of the extended agreement in yyyyMMdd integer format
     * @param agreementEndDate optional requested end date of the extended agreement in yyyyMMdd integer format
     * @param agreementType optional type of agreement being extended
     * @param numberOfSessionForPrescription1 optional number of sessions for the first prescription
     * @param numberOfSessionForPrescription2 optional number of sessions for the second prescription
     * @param attachments optional list of attachments including prescriptions and supporting documents
     * @return the electronic agreement extension response from the insurance organization, or null if no response
     */
    @Operation(
        summary = "Extend an agreement",
        description = "Requests an extension of an existing electronic agreement, providing updated prescriptions and session details."
    )
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
        @RequestParam prescriptionDate: Int,
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
            prescriptionDate = formatter.parseDateTime(prescriptionDate.toString()),
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    @Operation(
        summary = "Complete an agreement",
        description = "Completes a pending electronic agreement by providing additional required information such as prescriptions and attachments."
    )
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
        @RequestParam prescriberNihii: String,
        @RequestParam prescriberFirstName: String,
        @RequestParam prescriberLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam insuranceRef: String,
        @RequestParam prescriptionDate: Int,
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
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
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
            prescriptionDate = formatter.parseDateTime(prescriptionDate.toString()),
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    @Operation(
        summary = "Argue an agreement",
        description = "Submits additional arguments or justifications for a previously refused or pending electronic agreement request."
    )
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
        @RequestParam prescriberNihii: String,
        @RequestParam prescriberFirstName: String,
        @RequestParam prescriberLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam insuranceRef: String,
        @RequestParam prescriptionDate: Int,
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
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
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
            prescriptionDate = formatter.parseDateTime(prescriptionDate.toString()),
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    @Operation(
        summary = "Get agreement messages",
        description = "Retrieves pending asynchronous electronic agreement messages (responses, notifications) for the healthcare provider."
    )
    @PostMapping("async/getMessages", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMessageList(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpSpeciality: String
    ): EAgreementList?{
        return eagreementService.getMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            hcpQuality = hcpSpeciality
        )
    }

    @Operation(
        summary = "Confirm agreement messages",
        description = "Confirms receipt of asynchronous electronic agreement messages, marking them as processed."
    )
    @PostMapping("async/confirmMessage", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpSpeciality: String,
        @RequestBody eagreementMessagesReference: List<String>) : Boolean?{

        return eagreementService.confirmMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpQuality = hcpSpeciality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            eagreementMessagesReference = eagreementMessagesReference
        )
    }

}
