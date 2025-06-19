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

import be.fgov.ehealth.hubservices.core.v3.GetAccessRightResponse
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailResponse
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightResponse
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightResponse
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import ma.glasnost.orika.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.domain.consent.Consent
import org.taktik.freehealth.middleware.dto.hub.TransactionSummaryDto
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.hub.PutTransactionResponseDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import org.taktik.freehealth.middleware.service.HubService
import org.taktik.freehealth.utils.FuzzyValues
import java.util.*

@RestController
@RequestMapping("/hub")
class HubController(val hubService: HubService, val mapper: MapperFacade) {
    @ApiOperation(
        value = "Create or update a patient in the hubs",
        notes = "This endpoint allows to create or update a patient in the hubs. " +
                "It requires the healthcare provider's information and the patient's details." +
                "It allows a healthcare provider to register a patient in the hub system, " +
                "which is necessary for managing patient data and consent."
    )
    @PostMapping("/patient/{lastName}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putPatient(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare party")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare party")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare party")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare party")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare party")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The first name of the patient")
        @RequestParam firstName: String,
        @ApiParam("The last name of the patient")
        @PathVariable lastName: String,
        @ApiParam("The gender of the patient")
        @RequestParam gender: Gender,
        @ApiParam("The date of birth of the patient in YYYYMMDD format")
        @RequestParam dateOfBirth: Long,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. THis ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?
        ) = hubService.putPatient(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        dateOfBirth = FuzzyValues.getLocalDateTime(dateOfBirth)!!
    )

    @ApiOperation(
        value = "Get a patient from the hubs",
        notes = "This endpoint allows to retrieve a patient's information from the hubs. " +
                "It requires the healthcare provider's information and the patient's SSIN."
    )
    @GetMapping("/patient/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPatient(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare party")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare party")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare party")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare party")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare party")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?
    ) = hubService.getPatient(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin
    )

    @ApiOperation(
        value = "Get the consent status of a healthcare provider",
        notes = "This endpoint allows to retrieve the consent status of a healthcare provider that determines if an hcp has the right to access the hub. " +
                "It requires the healthcare provider's information."
    )
    @GetMapping("/hcpconsent/{hcpNihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getHcpConsent(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The Nihii of the healthcare provider")
        @PathVariable hcpNihii: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?
    ) = hubService.getHcPartyConsent(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip
    )

    @ApiOperation(
        value = "Register a patient consent",
        notes = "This endpoint allows a healthcare provider to register a patient's consent in the hub system. " +
                "It requires the healthcare provider's information and the patient's SSIN." +
                "It also requires some kind of proof of the patient's presence, such as an eid card number or isi card number for a child."
    )
    @PostMapping("/consent/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerPatientConsent(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("The eid card number of the patient, if available")
        @RequestParam(required = false) patientEidCardNumber: String?,
        @ApiParam("The isi card number of the patient, if available")
        @RequestParam(required = false) patientIsiCardNumber: String?
                              ) = hubService.registerPatientConsent(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber

                                                                   )
    @ApiOperation(
        value = "Revoke a patient's consent",
        notes = "This endpoint allows a healthcare provider to revoke a patient's consent in the hub system. " +
                "It requires the healthcare provider's information and the patient's SSIN." +
                "It also requires some kind of proof of the patient's presence, such as an eid card number or isi card number for a child."
    )
    @DeleteMapping("/consent/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun revokePatientConsent(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("The eid card number of the patient, if available")
        @RequestParam(required = false) patientEidCardNumber: String?,
        @ApiParam("The isi card number of the patient, if available")
        @RequestParam(required = false) patientIsiCardNumber: String?
                              ) = hubService.revokePatientConsent(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber
                                                                   )

    @ApiOperation(
        value = "Get a patient's consent",
        notes = "This endpoint allows to retrieve a patient's consent from the hubs, in other words, the patient's consent of having institutions and healthcare providers put some information on the hubs. " +
                "It requires the healthcare provider's information and the patient's SSIN."
    )
    @GetMapping("/consent/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPatientConsent(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?
    ): Consent? = hubService.getPatientConsent(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin
    )

    @ApiOperation(
        value = "Register a therapeutic link for a patient",
        notes = "A therapeutic link is a relationship between a healthcare provider and a patient that allows the healthcare provider to access the patient's data in the hub system. " +
                "This endpoint allows a healthcare provider to register a therapeutic link for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and some proof of presence such the eid card number, isi card number."
    )
    @PostMapping("/therlink/{hcpNihii}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerTherapeuticLink(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @PathVariable hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("The eid card number of the patient, if available")
        @RequestParam(required = false) patientEidCardNumber: String?,
        @ApiParam("The isi card number of the patient, if available")
        @RequestParam(required = false) patientIsiCardNumber: String?,
        @ApiParam("The type of the therapeutic link", defaultValue = "GP consultation")
        @RequestParam(required = false) therLinkType: String?,
        @ApiParam("The start date of the therapeutic link in format YYYYMMDD", example = "20250101")
        @RequestParam(required = false) from: Long?,
        @ApiParam("The end date of the therapeutic link in format YYYYMMDD", example = "20251231")
        @RequestParam(required = false) to: Long?
    ) = hubService.registerTherapeuticLink(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        therLinkType = therLinkType,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber,
        from = from,
        to = to
    )

    @ApiOperation(
        value = "Revoke a therapeutic link for a patient",
        notes = "This endpoint allows a healthcare provider to revoke a therapeutic link for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and some proof of presence such the eid card number, isi card number."
    )
    @DeleteMapping("/therlink/{hcpNihii}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun revokeTherapeuticLink(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @PathVariable hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("The eid card number of the patient, if available")
        @RequestParam(required = false) patientEidCardNumber: String?,
        @ApiParam("The isi card number of the patient, if available")
        @RequestParam(required = false) patientIsiCardNumber: String?,
        @ApiParam("The type of the therapeutic link to revoke", defaultValue = "GP consultation")
        @RequestParam(required = false) therLinkType: String?
        ) = hubService.revokeTherapeuticLink(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber,
        therLinkType = therLinkType
                                                                 )
    @ApiOperation(
        value = "Get therapeutic links for a patient",
        notes = "This endpoint allows to retrieve therapeutic links for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and some optional parameters such as the type of therapeutic link, start and end dates."
    )
    @GetMapping("/therlink/{hcpNihii}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTherapeuticLinks(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @PathVariable hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("The type of the therapeutic link to retrieve", defaultValue = "GP consultation")
        @RequestParam(required = false) therLinkType: String?,
        @ApiParam("The start date of the therapeutic link in format YYYYMMDD", example = "20250101")
        @RequestParam(required = false) from: Long?,
        @ApiParam("The end date of the therapeutic link in format YYYYMMDD", example = "20251231")
        @RequestParam(required = false) to: Long?
    ): TherapeuticLinkMessageDto? = hubService.getTherapeuticLinks(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        therLinkType = therLinkType,
        from = from,
        to = to
    ).let {
        mapper.map(it, TherapeuticLinkMessageDto::class.java)
    }


    @ApiOperation(
        value = "Get the list of transactions (documents, scans, ...) for a patient",
        notes = "This endpoint allows to retrieve the list of transactions for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the hub package ID. " +
                "The date range, author information, and transaction types can be provided to filter the results. "
    )
    @GetMapping("/list/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTransactionsList(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("The start date of the search for transactions in format YYYYMMDD", example = "20250101")
        @RequestParam(required = false) from: Long?,
        @ApiParam("The end date of the search for transactions in format YYYYMMDD", example = "20251231")
        @RequestParam(required = false) to: Long?,
        @ApiParam("The Nihii of the author of the transaction, optional")
        @RequestParam(required = false) authorNihii: String?,
        @ApiParam("The SSIN of the author of the transaction, optional")
        @RequestParam(required = false) authorSsin: String?,
        @ApiParam("Whether the transaction search is global (on all hubs through intrahub) or local (on the hub where the request is made)", defaultValue = "false")
        @RequestParam(required = false) isGlobal: Boolean?,
        @ApiParam("Allows a break the glass request (without a therapeutic link) by providing the reason for breaking the glass")
        @RequestParam(required = false) breakTheGlassReason: String?,
        @ApiParam("The list of transaction types to filter the results, such as 'document', 'scan', 'message', etc. If not provided, all transaction types are returned.")
        @RequestParam(required = false) transactionTypes: List<String>?
    ): List<TransactionSummaryDto> {
        return hubService.getTransactionsList(
            endpoint = endpoint,
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hubPackageId = hubPackageId,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpZip = hcpZip,
            hcpSsin = hcpSsin,
            ssin = patientSsin,
            from = from,
            to = to,
            authorNihii = authorNihii,
            authorSsin = authorSsin,
            isGlobal = isGlobal ?: false,
            breakTheGlassReason = breakTheGlassReason,
            transactionTypes = transactionTypes
        )
    }

    @ApiOperation(
        value = "Get a transaction (document, scan, ...) for a patient",
        notes = "This endpoint allows to retrieve a specific transaction for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the transaction ID."
    )
    @GetMapping("/t/{ssin}/{sv}/{sl}", produces = [MediaType.APPLICATION_XML_VALUE])
    fun getTransaction(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable ssin: String,
        @ApiParam("The SV part of the identifier of the transaction. Obtained from the transaction list.")
        @PathVariable sv: String,
        @ApiParam("The SL part of the identifier of the transaction. Obtained from the transaction list.")
        @PathVariable sl: String,
        @ApiParam("The ID of the transaction to retrieve")
        @RequestParam id: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("Allows a break the glass request (without a therapeutic link) by providing the reason for breaking the glass")
        @RequestParam(required = false) breakTheGlassReason: String?,
        @ApiParam("The ID of the hub (1990000035 for RSW, 1990000728 for Abrumet, 1990000134 for Cozo, 1990001916 for Vitalink, ...)")
        @RequestParam(required = false) externalHubId: String?,
        @ApiParam("The name of the hub, if applicable")
        @RequestParam(required = false) externalHubName: String?
    ): Kmehrmessage? {
        return hubService.getTransaction(
            endpoint = endpoint,
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hubPackageId = hubPackageId,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpZip = hcpZip,
            ssin = ssin,
            breakTheGlassReason = breakTheGlassReason,
            externalHubId = externalHubId,
            externalHubName = externalHubName,
            sv = sv,
            sl = sl,
            value = id
        )
    }

    @ApiOperation(
        value = "Get a transaction of type message for a patient converted to JSON for easy frontend parsing",
        notes = "This endpoint allows to retrieve a specific transaction message for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the transaction ID."
    )
    @GetMapping("/t/{ssin}/{sv}/{sl}/kmehr", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTransactionMessage(
        @ApiParam("Endpoint URL of the hub (e.g., https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, etc.)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable ssin: String,
        @ApiParam("The SV part of the identifier of the transaction. Obtained from the transaction list.")
        @PathVariable sv: String,
        @ApiParam("The SL part of the identifier of the transaction. Obtained from the transaction list.")
        @PathVariable sl: String,
        @ApiParam("The ID of the transaction to retrieve")
        @RequestParam id: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("Allows a break the glass request (without a therapeutic link) by providing the reason for breaking the glass")
        @RequestParam(required = false) breakTheGlassReason: String?,
        @ApiParam("The ID of the hub (1990000035 for RSW, 1990000728 for Abrumet, 1990000134 for Cozo, 1990001916 for Vitalink, ...)")
        @RequestParam(required = false) externalHubId: String?,
        @ApiParam("The name of the hub")
        @RequestParam(required = false) externalHubName: String?
    ): Kmehrmessage? {
        return hubService.getTransaction(
            endpoint = endpoint,
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hubPackageId = hubPackageId,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpZip = hcpZip,
            ssin = ssin,
            breakTheGlassReason = breakTheGlassReason,
            externalHubId = externalHubId,
            externalHubName = externalHubName,
            sv = sv,
            sl = sl,
            value = id
        )
    }

    @ApiOperation(
        value = "Delete a transaction (document, scan, ...) for a patient",
        notes = "This endpoint allows a healthcare provider to revoke a specific transaction for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the transaction ID." +
                "The actual deletion occurs if the healthcare provider has the right to do so, otherwise an error is triggered."
    )
    @DeleteMapping("/t/{ssin}/{sv}/{sl}")
    fun revokeTransaction(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable ssin: String,
        @ApiParam("The SV part of the identifier of the transaction. Obtained from the transaction list.")
        @PathVariable sv: String,
        @ApiParam("The SL part of the identifier of the transaction. Obtained from the transaction list.")
        @PathVariable sl: String,
        @ApiParam("The ID of the transaction to revoke")
        @RequestParam id: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("Allows a break the glass request (without a therapeutic link) by providing the reason for breaking the glass")
        @RequestParam(required = false) breakTheGlassReason: String?
    ): String {
        return hubService.revokeTransaction(
            endpoint = endpoint,
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hubPackageId = hubPackageId,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpZip = hcpZip,
            ssin = ssin,
            breakTheGlassReason = breakTheGlassReason,
            sv = sv,
            sl = sl,
            value = id
        )
    }

    @ApiOperation(
        value = "Put a transaction (document, scan, ...) on the hubs for a patient",
        notes = "This endpoint allows a healthcare provider to put a transaction for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the transaction message."
    )
    @PostMapping("/t/{hubId}/{patientSsin}", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putTransaction(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The ID of the hub (1990000035 for RSW, 1990000728 for Abrumet, 1990000134 for Cozo, 1990001916 for Vitalink, ...)")
        @PathVariable hubId: Long,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The transaction message in Kmehr format")
        @RequestBody message: ByteArray,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("Legacy, leave as null.")
        @RequestParam(required = false) hubApplication: String?
    ): PutTransactionResponseDto {
        return hubService.putTransaction(
            endpoint = endpoint,
            hubId = hubId,
            hubApplication = hubApplication ?: "",
            keystoreId = keystoreId,
            hubPackageId = hubPackageId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpZip = hcpZip,
            ssin = patientSsin,
            transaction = message
        ).let { mapper.map(it, PutTransactionResponseDto::class.java) }
    }

    @ApiOperation(
        value = "Get a transaction set (a collection of transactions) for a patient. This is mainly used for medication schemes.",
        notes = "This endpoint allows to retrieve a transaction set for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the transaction set identifier."
    )
    @GetMapping("/ts/{ssin}/{sv}/{sl}", produces = [MediaType.APPLICATION_XML_VALUE])
    fun getTransactionSet(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable ssin: String,
        @ApiParam("The SV part of the identifier of the transaction set. Obtained from the transaction list.")
        @PathVariable sv: String,
        @ApiParam("The SL part of the identifier of the transaction set. Obtained from the transaction list.")
        @PathVariable sl: String,
        @ApiParam("The ID of the transaction set to retrieve")
        @RequestParam id: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("Allows a break the glass request (without a therapeutic link) by providing the reason for breaking the glass")
        @RequestParam(required = false) breakTheGlassReason: String?,
        @ApiParam("The ID of the hub (1990000035 for RSW, 1990000728 for Abrumet, 1990000134 for Cozo, 1990001916 for Vitalink, ...)")
        @RequestParam(required = false) externalHubId: String?,
        @ApiParam("The name of the hub, if applicable")
        @RequestParam(required = false) externalHubName: String?
    ): Kmehrmessage? = hubService.getTransactionSet(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        ssin = ssin,
        breakTheGlassReason = breakTheGlassReason,
        externalHubId = externalHubId,
        externalHubName = externalHubName,
        sv = sv,
        sl = sl,
        value = id
    )

    @ApiOperation(
        value = "Get a transaction set (a collection of transactions) for a patient. This is mainly used for medication schemes. Returns the message in JSON format for easy frontend parsing.",
        notes = "This endpoint allows to retrieve a transaction set for a patient. " +
            "It requires the healthcare provider's information, the patient's SSIN, and the transaction set identifier."
    )
    @GetMapping("/ts/{ssin}/{sv}/{sl}/kmehr", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTransactionSetMessage(
        @ApiParam("Endpoint URL of the hub (e.g., https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, etc.)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SSIN of the patient")
        @PathVariable ssin: String,
        @ApiParam("The SV part of the identifier of the transaction set. Obtained from the transaction list.")
        @PathVariable sv: String,
        @ApiParam("The SL part of the identifier of the transaction set. Obtained from the transaction list.")
        @PathVariable sl: String,
        @ApiParam("The ID of the transaction set to retrieve")
        @RequestParam id: String,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("Allows a break the glass request (without a therapeutic link) by providing the reason for breaking the glass")
        @RequestParam(required = false) breakTheGlassReason: String?,
        @ApiParam("The ID of the hub (1990000035 for RSW, 1990000728 for Abrumet, 1990000134 for Cozo, 1990001916 for Vitalink, ...)")
        @RequestParam(required = false) externalHubId: String?,
        @ApiParam("The name of the hub")
        @RequestParam(required = false) externalHubName: String?
    ): Kmehrmessage? = hubService.getTransactionSet(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        ssin = ssin,
        breakTheGlassReason = breakTheGlassReason,
        externalHubId = externalHubId,
        externalHubName = externalHubName,
        sv = sv,
        sl = sl,
        value = id
    )

    @ApiOperation(
        value = "Put a transaction set (a collection of transactions) for a patient. This is mainly used for medication schemes.",
        notes = "This endpoint allows a healthcare provider to put a transaction set for a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the transaction set message."
    )
    @PostMapping("/ts/{hubId}/{patientSsin}", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putTransactionSet(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The ID of the hub (1990000035 for RSW, 1990000728 for Abrumet, 1990000134 for Cozo, 1990001916 for Vitalink, ...)")
        @PathVariable hubId: Long,
        @ApiParam("The SSIN of the patient")
        @PathVariable patientSsin: String,
        @ApiParam("The transaction set message in Kmehr format")
        @RequestBody message: ByteArray,
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("Legacy, leave as null.")
        @RequestParam(required = false) hubApplication: String?
    ): PutTransactionSetResponse = hubService.putTransactionSet(
        endpoint = endpoint,
        hubId = hubId,
        hubApplication = hubApplication ?: "",
        keystoreId = keystoreId,
        hubPackageId = hubPackageId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        ssin = patientSsin,
        transaction = message
    )

    @ApiOperation(
        value = "Get the audit trail of a patient",
        notes = "This endpoint allows to retrieve the audit trail of a patient. " +
                "It requires the healthcare provider's information, the patient's SSIN, and the hub package ID. The date range, author information, and transaction types can be provided to filter the results."
    )
    @GetMapping("/trail", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPatientAuditTrail(
        @ApiParam("value = 'The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)'")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The hub package ID, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam(required = false) hubPackageId: String?,
        @ApiParam("The start date of the search for transactions in format YYYYMMDD", example = "20250101")
        @RequestParam(required = false) from: Long?,
        @ApiParam("The end date of the search for transactions in format YYYYMMDD", example = "20251231")
        @RequestParam(required = false) to: Long?,
        @ApiParam("The Nihii of the author of the transaction, optional")
        @RequestParam(required = false) authorNihii: String?,
        @ApiParam("The SSIN of the author of the transaction, optional")
        @RequestParam(required = false) authorSsin: String?,
        @ApiParam("Whether the transaction search is global (on all hubs through intrahub) or local (on the hub where the request is made)", defaultValue = "false")
        @RequestParam(required = false) isGlobal: Boolean?,
        @ApiParam("Allows a break the glass request (without a therapeutic link) by providing the reason for breaking the glass")
        @RequestParam(required = false) breakTheGlassReason: String?,
        @ApiParam("The SSIN of the patient, optional")
        @RequestParam(required = false) ssin: String?,
        @ApiParam("The SV part of the identifier of the transaction, optional")
        @RequestParam(required = false) sv: String?,
        @ApiParam("The SL part of the identifier of the transaction, optional")
        @RequestParam(required = false) sl: String?,
        @ApiParam("The ID of the transaction, optional")
        @RequestParam(required = false) id: String?
    ): GetPatientAuditTrailResponse = hubService.getPatientAuditTrail(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        ssin = ssin,
        breakTheGlassReason = breakTheGlassReason,
        from = from,
        to = to,
        authorNihii = authorNihii,
        authorSsin = authorSsin,
        isGlobal = isGlobal ?: false,
        sv = sv,
        sl = sl,
        value = id,
        hubPackageId = hubPackageId
                                                                     )

    @ApiOperation(
        value = "Put an access right for a healthcare provider to access a patient's data",
        notes = "This endpoint allows a healthcare provider to manage access rights for another healthcare provider to access a patient's data. " +
                "It requires the healthcare provider's information, the identifiers of the document and the rights that need to be set."
    )
    @PostMapping("/access", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putAccessRight(
        @ApiParam("The endpoint URL of the hub (https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, https://services.cozo.be/IntrahubService/servicev3.asmx, https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService, ...)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SV part of the identifier of the transaction. Obtained from the transaction list.")
        @RequestParam sv: String, //trn to manage
        @ApiParam("The SL part of the identifier of the transaction. Obtained from the transaction list.")
        @RequestParam sl: String, //trn to manage
        @ApiParam("The value of the transaction to manage, usually the ID of the transaction")
        @RequestParam value: String, //trn to manage
        @ApiParam("The access right to set for the healthcare provider (allow, disallow)")
        @RequestParam accessRight: String, //allow, disallow
        @ApiParam("The Nihii of the healthcare provider to allow/disallow access, optional")
        @RequestParam (required = false) accessNihii: String?, //hcp to allow/disallow
        @ApiParam("The SSIN of the healthcare provider to allow/disallow access, optional")
        @RequestParam (required = false) accessSsin: String?, //hcp to allow/disallow
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam (required = false) hubPackageId: String?
    ): PutAccessRightResponse = hubService.putAccessRight(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        sv = sv,
        sl = sl,
        value = value,
        accessNihii = accessNihii,
        accessRight = accessRight,
        accessSsin = accessSsin,
        hubPackageId = hubPackageId
    )

    @ApiOperation(
        value = "Get the access right for a healthcare provider to access a patient's data",
        notes = "This endpoint allows a healthcare provider to check the access rights of another healthcare provider to access a patient's data. " +
                "It requires the healthcare provider's information and the identifiers of the document and the rights that need to be checked."
    )
    @GetMapping("/access", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAccessRight(
        @ApiParam("Endpoint URL of the hub (e.g., https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, etc.)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId")keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase")passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SV part of the identifier of the transaction. Obtained from the transaction list.")
        @RequestParam sv: String, //trn to manage
        @ApiParam("The SL part of the identifier of the transaction. Obtained from the transaction list.")
        @RequestParam sl: String, //trn to manage
        @ApiParam("The value of the transaction to manage, usually the ID of the transaction")
        @RequestParam value: String, //trn to manage
        @ApiParam("The hub package ID, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam (required = false) hubPackageId: String?
    ): GetAccessRightResponse = hubService.getAccessRight(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        sv = sv,
        sl = sl,
        value = value,
        hubPackageId = hubPackageId
                                                         )

    @ApiOperation(
        value = "Revoke an access right for a healthcare provider to access a patient's data",
        notes = "This endpoint allows a healthcare provider to revoke access rights for another healthcare provider to access a patient's data. " +
                "It requires the healthcare provider's information, the identifiers of the document and the identifier of the healthcare provider whose access needs to be revoked."
    )
    @DeleteMapping("/access")
    fun revokeAccessRight(
        @ApiParam("Endpoint URL of the hub (e.g., https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx, https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx, etc.)")
        @RequestParam endpoint: String,
        @ApiParam("The keystore ID for the certificate of the healthcare provider")
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider")
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore")
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("The last name of the healthcare provider")
        @RequestParam hcpLastName: String,
        @ApiParam("The first name of the healthcare provider")
        @RequestParam hcpFirstName: String,
        @ApiParam("The Nihii of the healthcare provider")
        @RequestParam hcpNihii: String,
        @ApiParam("The SSIN of the healthcare provider")
        @RequestParam hcpSsin: String,
        @ApiParam("The ZIP code of the healthcare provider")
        @RequestParam hcpZip: String,
        @ApiParam("The SV part of the identifier of the transaction. Obtained from the transaction list.")
        @RequestParam sv: String, //trn to manage
        @ApiParam("The SL part of the identifier of the transaction. Obtained from the transaction list.")
        @RequestParam sl: String, //trn to manage
        @ApiParam("The value of the transaction to manage, usually the ID of the transaction")
        @RequestParam value: String, //trn to manage
        @ApiParam("The Nihii of the healthcare provider to allow/disallow access, optional")
        @RequestParam (required = false) accessNihii: String?, //hcp to allow/disallow
        @ApiParam("The SSIN of the healthcare provider to allow/disallow access, optional")
        @RequestParam (required = false) accessSsin: String?, //hcp to allow/disallow
        @ApiParam("The ID of the software package used to access the hub, provided by the hub. This ID is usually different for acceptance and production environments.")
        @RequestParam (required = false) hubPackageId: String?
    ): RevokeAccessRightResponse = hubService.revokeAccessRight(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        sv = sv,
        sl = sl,
        value = value,
        accessNihii = accessNihii,
        accessSsin = accessSsin,
        hubPackageId = hubPackageId
                                                               )

    @ApiOperation(
        value = "Utlity method to convert Kmehr XML to JSON",
        notes = "This endpoint converts a Kmehr XML message to a JSON representation. " +
                "It is useful for applications that need to process Kmehr messages in JSON format."
    )
    @PostMapping("/convertKmehrXMLtoJSON", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun convertKmehrXMLtoJSON(@RequestBody message: ByteArray): Kmehrmessage {
        return MarshallerHelper(Kmehrmessage::class.java, Kmehrmessage::class.java).toObject(message)
    }

}
