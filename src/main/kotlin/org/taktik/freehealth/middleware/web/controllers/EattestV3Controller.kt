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

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import org.taktik.freehealth.middleware.service.EattestV3Service
import java.util.UUID

/**
 * Controller for the Belgian eHealth electronic attestation V3 (eattestv3) service.
 *
 * Provides endpoints for healthcare providers to submit and cancel electronic
 * attestations of care using the V3 protocol. Adds support for healthcare provider
 * quality, RCAM patient flag, attempt number, and decision reference compared to V2.
 */
@RestController
@RequestMapping("/eattestv3")
@Tag(name = "eattestv3", description = "Electronic attestation of care V3 operations for submitting and cancelling healthcare attestations via the Belgian eHealth platform, with support for provider quality and decision references.")
class EattestV3Controller(val eattestService: EattestV3Service) {
    /**
     * Sends an electronic attestation of care (V3) to the Belgian mutuality/insurance organization
     * and returns the full verbose response including acknowledgement details, invoicing number, and raw attestation data.
     *
     * The V3 protocol extends V2 with support for healthcare provider quality, RCAM patient flag,
     * attempt number, and decision reference fields.
     *
     * @param patientSsin the patient's SSIN (Social Security Identification Number)
     * @param keystoreId UUID of the uploaded PKCS12 keystore (obtained via STS uploadKeystore endpoint)
     * @param tokenId UUID of the SAML authentication token (obtained via STS requestToken endpoint)
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (Social Security Identification Number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param hcpCbe healthcare provider's CBE (Crossroads Bank for Enterprises) number
     * @param hcpQuality optional provider quality/role (e.g. "doctor", "dentist")
     * @param patientFirstName the patient's first name
     * @param patientLastName the patient's last name
     * @param patientGender the patient's gender
     * @param isPatientRcam optional flag indicating whether the patient falls under RCAM (Caisse des soins de sante) regime
     * @param date optional epoch timestamp for the attestation date; defaults to the current date if null
     * @param treatmentReason optional reason for the treatment
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @param guardPostNihii optional NIHII of the guard post (poste de garde)
     * @param guardPostSsin optional SSIN of the guard post
     * @param guardPostName optional name of the guard post
     * @param attemptNbr optional attempt number for resubmission of a previously failed attestation
     * @param decisionReference optional reference to a prior authorization decision (e.g. from Chapter IV / eAgreement)
     * @param attest the attestation payload containing the medical acts to attest
     * @return the full attestation response including acknowledgement, invoicing number, and raw attestation data
     */
    @Operation(
        summary = "Send an electronic attestation V3 with verbose response",
        description = "Submits an electronic attestation of care (V3) for a patient identified by SSIN to the Belgian eHealth platform and returns the full response including acknowledgement details, invoicing number, and raw attestation data. Supports additional V3 fields such as healthcare provider quality, RCAM patient flag, attempt number, and decision reference."
    )
    @PostMapping("/send/{patientSsin}/verbose", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendAttestWithResponse(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpCbe: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) isPatientRcam: Boolean?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) treatmentReason: String?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?,
        @RequestParam(required = false) guardPostNihii: String?,
        @RequestParam(required = false) guardPostSsin: String?,
        @RequestParam(required = false) guardPostName: String?,
        @RequestParam(required = false) attemptNbr: Int?,
        @RequestParam(required = false) decisionReference: String?,
        @RequestBody attest: Eattest
    ) = eattestService.sendAttestV3(
        keystoreId,
        tokenId,
        hcpNihii,
        hcpSsin,
        hcpFirstName,
        hcpLastName,
        hcpCbe,
        hcpQuality,
        treatmentReason,
        traineeSupervisorSsin,
        traineeSupervisorNihii,
        traineeSupervisorFirstName,
        traineeSupervisorLastName,
        guardPostNihii,
        guardPostSsin,
        guardPostName,
        passPhrase,
        patientSsin,
        patientFirstName,
        patientLastName,
        patientGender,
        isPatientRcam ?: false,
        date,
        attemptNbr,
        decisionReference,
        attest
    )

    /**
     * Sends an electronic attestation of care (V3) to the Belgian mutuality/insurance organization
     * and returns a simplified result containing only the acknowledgement, invoicing number, and attestation data.
     *
     * Unlike the verbose variant, this method extracts and returns only the essential fields from the response.
     * The V3 protocol extends V2 with support for healthcare provider quality, RCAM patient flag,
     * attempt number, and decision reference fields.
     *
     * @param patientSsin the patient's SSIN (Social Security Identification Number)
     * @param keystoreId UUID of the uploaded PKCS12 keystore (obtained via STS uploadKeystore endpoint)
     * @param tokenId UUID of the SAML authentication token (obtained via STS requestToken endpoint)
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (Social Security Identification Number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param hcpCbe healthcare provider's CBE (Crossroads Bank for Enterprises) number
     * @param hcpQuality optional provider quality/role (e.g. "doctor", "dentist")
     * @param patientFirstName the patient's first name
     * @param patientLastName the patient's last name
     * @param patientGender the patient's gender
     * @param isPatientRcam optional flag indicating whether the patient falls under RCAM (Caisse des soins de sante) regime
     * @param date optional epoch timestamp for the attestation date; defaults to the current date if null
     * @param treatmentReason optional reason for the treatment
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @param guardPostNihii optional NIHII of the guard post (poste de garde)
     * @param guardPostSsin optional SSIN of the guard post
     * @param guardPostName optional name of the guard post
     * @param attemptNbr optional attempt number for resubmission of a previously failed attestation
     * @param decisionReference optional reference to a prior authorization decision (e.g. from Chapter IV / eAgreement)
     * @param attest the attestation payload containing the medical acts to attest
     * @return a simplified [SendAttestResult] with acknowledgement, invoicing number, and attestation data, or null if the service returns no result
     */
    @Operation(
        summary = "Send an electronic attestation V3",
        description = "Submits an electronic attestation of care (V3) for a patient identified by SSIN to the Belgian eHealth platform. Returns a simplified result containing the acknowledgement, invoicing number, and attestation data. Supports additional V3 fields such as healthcare provider quality, RCAM patient flag, attempt number, and decision reference."
    )
    @PostMapping("/send/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendAttest(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpCbe: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) isPatientRcam: Boolean?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) treatmentReason: String?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?,
        @RequestParam(required = false) guardPostNihii: String?,
        @RequestParam(required = false) guardPostSsin: String?,
        @RequestParam(required = false) guardPostName: String?,
        @RequestParam(required = false) attemptNbr: Int?,
        @RequestParam(required = false) decisionReference: String?,
        @RequestBody attest: Eattest
    ): SendAttestResult? = eattestService.sendAttestV3(
        keystoreId,
        tokenId,
        hcpNihii,
        hcpSsin,
        hcpFirstName,
        hcpLastName,
        hcpCbe,
        hcpQuality,
        treatmentReason,
        traineeSupervisorSsin,
        traineeSupervisorNihii,
        traineeSupervisorFirstName,
        traineeSupervisorLastName,
        guardPostNihii,
        guardPostSsin,
        guardPostName,
        passPhrase,
        patientSsin,
        patientFirstName,
        patientLastName,
        patientGender,
        isPatientRcam ?: false,
        date,
        attemptNbr,
        decisionReference,
        attest
    )?.let { SendAttestResult(it.acknowledge, it.invoicingNumber, it.attest) }

    /**
     * Cancels a previously submitted electronic attestation of care (V3) for a given patient.
     *
     * The cancellation requires the original attestation reference and a reason for cancellation.
     * Returns a simplified result containing only the acknowledgement, invoicing number, and attestation data.
     *
     * @param patientSsin the patient's SSIN (Social Security Identification Number)
     * @param keystoreId UUID of the uploaded PKCS12 keystore (obtained via STS uploadKeystore endpoint)
     * @param tokenId UUID of the SAML authentication token (obtained via STS requestToken endpoint)
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (Social Security Identification Number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param hcpCbe healthcare provider's CBE (Crossroads Bank for Enterprises) number
     * @param patientFirstName the patient's first name
     * @param patientLastName the patient's last name
     * @param patientGender the patient's gender
     * @param eAttestRef the reference identifier of the attestation to cancel
     * @param reason the reason for cancelling the attestation
     * @param date optional epoch timestamp for the cancellation date; defaults to the current date if null
     * @param attemptNbr optional attempt number for resubmission of a previously failed cancellation
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @return a simplified [SendAttestResult] with acknowledgement, invoicing number, and attestation data, or null if the service returns no result
     */
    @Operation(
        summary = "Cancel an electronic attestation V3",
        description = "Cancels a previously submitted electronic attestation of care (V3) for a patient identified by SSIN. Requires the attestation reference and a cancellation reason. Returns a simplified result containing the acknowledgement, invoicing number, and attestation data."
    )
    @DeleteMapping("/send/{patientSsin}")
    fun cancelAttest(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpCbe: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam eAttestRef : String,
        @RequestParam reason : String,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) attemptNbr: Int?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?
                  ): SendAttestResult? =
        eattestService.cancelAttest(
            keystoreId,
            tokenId,
            hcpNihii,
            hcpSsin,
            hcpFirstName,
            hcpLastName,
            hcpCbe,
            traineeSupervisorSsin,
            traineeSupervisorNihii,
            traineeSupervisorFirstName,
            traineeSupervisorLastName,
            passPhrase,
            patientSsin,
            patientFirstName,
            patientLastName,
            patientGender,
            null,
            eAttestRef,
            reason,
            attemptNbr
       )?.let { SendAttestResult(it.acknowledge, it.invoicingNumber, it.attest) }

    /**
     * Cancels a previously submitted electronic attestation of care (V3) for a given patient
     * and returns the full verbose response including all acknowledgement details and raw attestation data.
     *
     * The cancellation requires the original attestation reference and a reason for cancellation.
     *
     * @param patientSsin the patient's SSIN (Social Security Identification Number)
     * @param keystoreId UUID of the uploaded PKCS12 keystore (obtained via STS uploadKeystore endpoint)
     * @param tokenId UUID of the SAML authentication token (obtained via STS requestToken endpoint)
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (Social Security Identification Number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param hcpCbe healthcare provider's CBE (Crossroads Bank for Enterprises) number
     * @param patientFirstName the patient's first name
     * @param patientLastName the patient's last name
     * @param patientGender the patient's gender
     * @param eAttestRef the reference identifier of the attestation to cancel
     * @param reason the reason for cancelling the attestation
     * @param date optional epoch timestamp for the cancellation date; defaults to the current date if null
     * @param attemptNbr optional attempt number for resubmission of a previously failed cancellation
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @return the full cancellation response including acknowledgement, invoicing number, and raw attestation data
     */
    @Operation(
        summary = "Cancel an electronic attestation V3 with verbose response",
        description = "Cancels a previously submitted electronic attestation of care (V3) for a patient identified by SSIN and returns the full response. Requires the attestation reference and a cancellation reason."
    )
    @DeleteMapping("/send/{patientSsin}/verbose")
    fun cancelAttestWithResponse(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpCbe: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam eAttestRef : String,
        @RequestParam reason : String,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) attemptNbr: Int?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?
                    ) =
        eattestService.cancelAttest(
            keystoreId,
            tokenId,
            hcpNihii,
            hcpSsin,
            hcpFirstName,
            hcpLastName,
            hcpCbe,
            traineeSupervisorSsin,
            traineeSupervisorNihii,
            traineeSupervisorFirstName,
            traineeSupervisorLastName,
            passPhrase,
            patientSsin,
            patientFirstName,
            patientLastName,
            patientGender,
            null,
            eAttestRef,
            reason,
            attemptNbr
                                   )
}
