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

import be.fgov.ehealth.hubservices.core.v2.ConsentType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.consent.ConsentMessageDto
import org.taktik.freehealth.middleware.dto.consent.ConsentTypeDto
import org.taktik.freehealth.middleware.service.ConsentService
import java.util.*

/**
 * REST controller for managing patient consent on the Belgian eHealth platform.
 *
 * This controller provides endpoints to register, retrieve, and revoke a patient's consent for sharing
 * their health data through the eHealth platform. Patient consent is a prerequisite for accessing
 * shared health data (e.g., SumEHR, medication schemes) stored in health vaults and hubs.
 *
 * Patient identification can optionally be strengthened using the Belgian electronic identity card (eID)
 * or the ISI+ social insurance card.
 *
 * All endpoints require authentication via keystore, token, and passphrase headers.
 */
@RestController
@RequestMapping("/consent")
@Tag(name = "Consent", description = "Patient consent management: register, retrieve, and revoke patient consent for sharing health data through the Belgian eHealth platform.")
class ConsentController(val consentService: ConsentService, val mapper: MapperFacade) {
    /**
     * Registers a patient's consent for sharing their health data through the Belgian eHealth platform.
     * Once registered, health data such as SumEHR documents and medication schemes can be shared
     * between healthcare providers via the eHealth hubs. Patient identification can optionally be
     * strengthened by providing the eID or ISI+ card number.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientSsin patient's SSIN (social security number)
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param eidCardNumber Belgian electronic identity card number, optional (strengthens patient identification)
     * @param isiCardNumber ISI+ social insurance card number, optional (strengthens patient identification)
     * @return a [ConsentMessageDto] containing the result of the consent registration, including any error or acknowledgement details
     */
    @Operation(
        summary = "Register patient consent",
        description = "Registers a patient's consent for sharing their health data through the Belgian eHealth platform, optionally using eID or ISI+ card identification."
    )
    @PostMapping("/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerPatientConsent(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable patientSsin: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam(required = false) eidCardNumber: String?,
        @RequestParam(required = false) isiCardNumber: String?
    ): ConsentMessageDto = consentService.registerPatientConsent(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        patientSsin = patientSsin,
        patientFirstName = patientFirstName,
        patientLastName = patientLastName,
        eidCardNumber = eidCardNumber,
        isiCardNumber = isiCardNumber
                                                  ).let { mapper.map(it, ConsentMessageDto::class.java) }

    /**
     * Retrieves the current consent status for a patient, indicating whether they have given consent
     * for sharing their health data through the Belgian eHealth platform. This can be used to verify
     * consent before attempting to access shared health data in hubs.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientSsin patient's SSIN (social security number)
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @return a [ConsentMessageDto] containing the consent status and details, or an indication that no consent exists
     */
    @Operation(
        summary = "Get patient consent",
        description = "Retrieves the current consent status for a patient, indicating whether they have given consent for sharing their health data."
    )
    @GetMapping("/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPatientConsent(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable patientSsin: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String
    ): ConsentMessageDto = consentService.getPatientConsent(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        patientSsin = patientSsin,
        patientFirstName = patientFirstName,
        patientLastName = patientLastName
                                             ).let { mapper.map(it, ConsentMessageDto::class.java) }

    @Operation(
        summary = "Revoke patient consent",
        description = "Revokes an existing patient consent for sharing health data through the Belgian eHealth platform, using the existing consent details provided in the request body."
    )
    @PostMapping("/revoke", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun revokePatientConsent(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestBody existingConsent: ConsentTypeDto,
        @RequestParam(required = false) eidCardNumber: String?,
        @RequestParam(required = false) isiCardNumber: String?
    ): ConsentMessageDto = consentService.revokePatientConsent(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        existingConsent = mapper.map(existingConsent, ConsentType::class.java),
        eidCardNumber = eidCardNumber,
        isiCardNumber = isiCardNumber
                                                ).let { mapper.map(it, ConsentMessageDto::class.java) }
}
