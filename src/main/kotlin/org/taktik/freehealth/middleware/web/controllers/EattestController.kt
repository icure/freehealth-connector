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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import org.taktik.freehealth.middleware.service.EattestService
import org.taktik.freehealth.middleware.service.EattestV2Service
import java.util.UUID
import jakarta.servlet.http.HttpServletRequest

/**
 * Controller for the Belgian eHealth electronic attestation (eattest) service.
 *
 * Provides endpoints for healthcare providers to submit electronic attestations
 * of care to mutuality/insurance organizations via the eHealth platform.
 * This controller delegates to the V2 implementation of the eattest service.
 */
@RestController
@RequestMapping("/eattest")
@Tag(name = "eattest", description = "Electronic attestation of care (eattest) operations for submitting healthcare attestations to Belgian mutuality/insurance organizations.")
class EattestController(val eattestService: EattestV2Service) {
    /**
     * Sends an electronic attestation of care to the Belgian mutuality/insurance organization
     * and returns the full verbose response including acknowledgement details and raw attestation data.
     *
     * This endpoint delegates to the V2 eattest service implementation. The verbose variant returns
     * the complete [SendAttestResult] with all response fields, as opposed to the simplified version.
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
        summary = "Send an electronic attestation with verbose response",
        description = "Submits an electronic attestation of care for a patient identified by SSIN to the Belgian eHealth platform and returns the full response including acknowledgement details and raw attestation data."
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
        "",
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
     * Sends an electronic attestation of care to the Belgian mutuality/insurance organization
     * and returns a simplified result containing only the acknowledgement, invoicing number, and attestation data.
     *
     * This endpoint delegates to the V2 eattest service implementation. Unlike the verbose variant,
     * this method extracts and returns only the essential fields from the response.
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
        summary = "Send an electronic attestation",
        description = "Submits an electronic attestation of care for a patient identified by SSIN to the Belgian eHealth platform. Returns a simplified result containing the acknowledgement, invoicing number, and attestation data."
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
        "",
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
}
