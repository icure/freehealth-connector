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
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EattestV2Service
import java.util.UUID
import jakarta.servlet.http.HttpServletRequest

/**
 * Controller for the Belgian eHealth electronic attestation V2 (eattestv2) service.
 *
 * Provides endpoints for healthcare providers to submit and cancel electronic
 * attestations of care using the V2 protocol. Supports treatment reason and
 * trainee supervisor information.
 */
@RestController
@RequestMapping("/eattestv2")
@Tag(name = "eattestv2", description = "Electronic attestation of care V2 operations for submitting and cancelling healthcare attestations via the Belgian eHealth platform.")
class EattestV2Controller(val eattestService: EattestV2Service) {
    /**
     * Sends an electronic attestation of care (V2) to the Belgian mutuality/insurance organization
     * and returns the full verbose response including acknowledgement details, invoicing number, and raw attestation data.
     *
     * The V2 protocol adds support for treatment reason and trainee supervisor information
     * compared to the base eattest protocol.
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
     * @param treatmentReason optional reason for the treatment
     * @param date optional epoch timestamp for the attestation date; defaults to the current date if null
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @param guardPostNihii optional NIHII of the guard post (poste de garde)
     * @param guardPostSsin optional SSIN of the guard post
     * @param guardPostName optional name of the guard post
     * @param attest the attestation payload containing the medical acts to attest
     * @return the full attestation response including acknowledgement, invoicing number, and raw attestation data
     */
    @Operation(
        summary = "Send an electronic attestation V2 with verbose response",
        description = "Submits an electronic attestation of care (V2) for a patient identified by SSIN to the Belgian eHealth platform and returns the full response including acknowledgement details, invoicing number, and raw attestation data."
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
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) treatmentReason: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?,
        @RequestParam(required = false) guardPostNihii: String?,
        @RequestParam(required = false) guardPostSsin: String?,
        @RequestParam(required = false) guardPostName: String?,
        @RequestBody attest: Eattest
    ) = eattestService.sendAttestV2(
        keystoreId,
        tokenId,
        hcpNihii,
        hcpSsin,
        hcpFirstName,
        hcpLastName,
        hcpCbe,
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
        null,
        attest
    )

    /**
     * Sends an electronic attestation of care (V2) to the Belgian mutuality/insurance organization
     * and returns a simplified result containing only the acknowledgement, invoicing number, and attestation data.
     *
     * Unlike the verbose variant, this method extracts and returns only the essential fields from the response.
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
     * @param treatmentReason optional reason for the treatment
     * @param date optional epoch timestamp for the attestation date; defaults to the current date if null
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @param guardPostNihii optional NIHII of the guard post (poste de garde)
     * @param guardPostSsin optional SSIN of the guard post
     * @param guardPostName optional name of the guard post
     * @param attest the attestation payload containing the medical acts to attest
     * @return a simplified [SendAttestResult] with acknowledgement, invoicing number, and attestation data, or null if the service returns no result
     */
    @Operation(
        summary = "Send an electronic attestation V2",
        description = "Submits an electronic attestation of care (V2) for a patient identified by SSIN to the Belgian eHealth platform. Returns a simplified result containing the acknowledgement, invoicing number, and attestation data."
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
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) treatmentReason: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?,
        @RequestParam(required = false) guardPostNihii: String?,
        @RequestParam(required = false) guardPostSsin: String?,
        @RequestParam(required = false) guardPostName: String?,
        @RequestBody attest: Eattest
    ): SendAttestResult? = eattestService.sendAttestV2(
        keystoreId,
        tokenId,
        hcpNihii,
        hcpSsin,
        hcpFirstName,
        hcpLastName,
        hcpCbe,
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
        null,
        attest
    )?.let { SendAttestResult(it.acknowledge, it.invoicingNumber, it.attest) }

    /**
     * Cancels a previously submitted electronic attestation of care (V2) for a given patient.
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
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @return a simplified [SendAttestResult] with acknowledgement, invoicing number, and attestation data, or null if the service returns no result
     */
    @Operation(
        summary = "Cancel an electronic attestation V2",
        description = "Cancels a previously submitted electronic attestation of care (V2) for a patient identified by SSIN. Requires the attestation reference and a cancellation reason. Returns a simplified result containing the acknowledgement, invoicing number, and attestation data."
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
            reason
       )?.let { SendAttestResult(it.acknowledge, it.invoicingNumber, it.attest) }

    /**
     * Cancels a previously submitted electronic attestation of care (V2) for a given patient
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
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @return the full cancellation response including acknowledgement, invoicing number, and raw attestation data
     */
    @Operation(
        summary = "Cancel an electronic attestation V2 with verbose response",
        description = "Cancels a previously submitted electronic attestation of care (V2) for a patient identified by SSIN and returns the full response. Requires the attestation reference and a cancellation reason."
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
            reason
                                   )
}
