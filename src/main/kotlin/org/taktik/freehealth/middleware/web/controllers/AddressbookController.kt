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

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.service.AddressbookService
import java.util.*

/**
 * REST controller for querying the Belgian eHealth Addressbook.
 *
 * Provides endpoints to search for and retrieve details of healthcare professionals (HCPs)
 * and organizations registered in the Belgian eHealth platform addressbook. Lookups can be
 * performed by name (phonetic search), NIHII number, SSIN, CBE number, or EHP identifier.
 *
 * All endpoints require a valid keystore and SAML token, supplied via HTTP headers.
 */
@RestController
@RequestMapping("/ab")
@Tag(name = "Addressbook", description = "Search for healthcare professionals and organizations in the Belgian eHealth addressbook.")
class AddressbookController(val addressbookService: AddressbookService) {
    /**
     * Searches the Belgian eHealth addressbook for healthcare professionals matching a last name.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param lastName last name to search for in the addressbook
     * @param firstName optional first name to narrow the search results
     * @param type optional HCP type filter (e.g. "PHYSICIAN", "DENTIST"); defaults to "PHYSICIAN" if not specified
     * @return a list of [HealthcareParty] entries matching the search criteria
     */
    @Operation(
        summary = "Search healthcare professionals by last name",
        description = "Searches the Belgian eHealth addressbook for healthcare professionals matching the given last name, with optional first name and type filters."
    )
    @GetMapping("/search/hcp/{lastName}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchHcp(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable lastName: String,
        @RequestParam(required = false) firstName: String?,
        @RequestParam(required = false) type: String?
    ): List<HealthcareParty> = addressbookService.searchHcp(
        keystoreId, tokenId, passPhrase, lastName, firstName, type ?: "PHYSICIAN"
    )

    /**
     * Searches the Belgian eHealth addressbook for organizations matching a name.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param name organization name to search for in the addressbook
     * @param type optional organization type filter (e.g. "HOSPITAL", "PHARMACY"); defaults to "HOSPITAL" if not specified
     * @return a list of [HealthcareParty] entries representing matching organizations
     */
    @Operation(
        summary = "Search organizations by name",
        description = "Searches the Belgian eHealth addressbook for organizations matching the given name, with an optional type filter (defaults to HOSPITAL)."
    )
    @GetMapping("/search/org/{name}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchOrg(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable name: String,
        @RequestParam(required = false) type: String?
    ): List<HealthcareParty> = addressbookService.searchOrg(
        keystoreId, tokenId, passPhrase, name, type ?: "HOSPITAL"
    )

    /**
     * Retrieves a healthcare professional from the addressbook by their NIHII number.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param nihii NIHII number (unique Belgian healthcare provider identification number)
     * @param language optional language code for the response (e.g. "fr", "nl", "de"); defaults to "fr"
     * @return the matching [HealthcareParty], or null if no match is found
     */
    @Operation(
        summary = "Get a healthcare professional by NIHII number",
        description = "Retrieves a healthcare professional's details from the Belgian eHealth addressbook using their NIHII identification number."
    )
    @GetMapping("/hcp/nihii/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getHcpByNihii(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable nihii: String,
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getHcp(
        keystoreId, tokenId, passPhrase, nihii, null, null, language ?: "fr"
    )

    /**
     * Retrieves a healthcare professional from the addressbook by their SSIN.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param ssin social security identification number (NISS) of the healthcare professional
     * @param quality optional healthcare provider quality for authorization purposes
     * @param language optional language code for the response (e.g. "fr", "nl", "de"); defaults to "fr"
     * @return the matching [HealthcareParty], or null if no match is found
     */
    @Operation(
        summary = "Get a healthcare professional by SSIN",
        description = "Retrieves a healthcare professional's details from the Belgian eHealth addressbook using their SSIN (social security identification number)."
    )
    @GetMapping("/hcp/ssin/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getHcpBySsin(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable ssin: String,
        @RequestParam(required = false) quality: String?,
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getHcp(
        keystoreId, tokenId, passPhrase, null, ssin, quality, language ?: "fr"
    )

    /**
     * Retrieves an organization from the addressbook by its NIHII number.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param nihii NIHII number (unique Belgian healthcare provider identification number) of the organization
     * @param language optional language code for the response (e.g. "fr", "nl", "de"); defaults to "fr"
     * @return the matching [HealthcareParty], or null if no match is found
     */
    @Operation(
        summary = "Get an organization by NIHII number",
        description = "Retrieves an organization's details from the Belgian eHealth addressbook using its NIHII identification number."
    )
    @GetMapping("/org/nihii/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getOrgByNihii(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable nihii: String,
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, null, null, nihii, language ?: "fr"
    )

    /**
     * Retrieves an organization from the addressbook by its CBE number.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param cbe Belgian company registration number (Crossroads Bank for Enterprises / Banque-Carrefour des Entreprises)
     * @param language optional language code for the response (e.g. "fr", "nl", "de"); defaults to "fr"
     * @return the matching [HealthcareParty], or null if no match is found
     */
    @Operation(
        summary = "Get an organization by CBE number",
        description = "Retrieves an organization's details from the Belgian eHealth addressbook using its CBE (Crossroads Bank for Enterprises) number."
    )
    @GetMapping("/org/cbe/{cbe}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getOrgByCbe(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable cbe: String?,
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, null, cbe, null, language ?: "fr"
    )

    /**
     * Retrieves an organization from the addressbook by its EHP identifier.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param ehp eHealth Platform (EHP) identifier of the organization
     * @param language optional language code for the response (e.g. "fr", "nl", "de"); defaults to "fr"
     * @return the matching [HealthcareParty], or null if no match is found
     */
    @Operation(
        summary = "Get an organization by EHP number",
        description = "Retrieves an organization's details from the Belgian eHealth addressbook using its EHP (eHealth Platform) identifier."
    )
    @GetMapping("/org/ehp/{ehp}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getOrgByEhp(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable ehp: String?,
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, ehp, null, null, language ?: "fr"
    )
}
