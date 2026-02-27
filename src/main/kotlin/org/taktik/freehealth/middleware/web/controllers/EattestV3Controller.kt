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
