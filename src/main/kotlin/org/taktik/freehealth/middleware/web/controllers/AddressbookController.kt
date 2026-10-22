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

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.service.AddressbookService
import java.util.*

@RestController
@RequestMapping("/ab")
class AddressbookController(val addressbookService: AddressbookService) {
    @ApiOperation(
        value = "Search healthcare professionals by last name (legacy)",
        notes = "Searches the eHealth address book by family name only. The name accepts a '*' wildcard (Steeman*). " +
            "The profession defaults to PHYSICIAN when 'type' is omitted, so this endpoint cannot search across " +
            "every profession — use GET /ab/search/hcp for that, and for any criterion other than a name."
    )
    @GetMapping("/search/hcp/{lastName}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchHcp(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("Family name of the professional, '*' wildcard allowed", required = true)
        @PathVariable lastName: String,
        @ApiParam("First name of the professional, '*' wildcard allowed")
        @RequestParam(required = false) firstName: String?,
        @ApiParam("Profession code, e.g. PHYSICIAN, PHYSIOTHERAPIST, NURSE. Defaults to PHYSICIAN")
        @RequestParam(required = false) type: String?
    ): List<HealthcareParty> = addressbookService.searchHcp(
        keystoreId, tokenId, passPhrase, lastName, firstName, type ?: "PHYSICIAN"
    )

    @ApiOperation(
        value = "Search healthcare professionals on any combination of criteria",
        notes = "Broad search in the eHealth address book: every criterion is optional, only the empty query is " +
            "rejected. NIHII/SSIN and city/zipCode are xs:choice in the schema, so each pair is mutually exclusive " +
            "(400). Beyond that the service decides, and it is stricter than its own schema: it wants a profession " +
            "unless you search by nihii or ssin, and it refuses a name and a location together, answering " +
            "'This combination of search criteria is not supported.' Names accept a '*' wildcard (Steeman*). Results " +
            "carry identity, nihii, professionCodes and speciality only — no address and no eHealthBox: resolve " +
            "those with GET /ab/hcp/nihii/{nihii} once a result has been picked."
    )
    @GetMapping("/search/hcp", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchHcpByCriteria(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("Family name of the professional, '*' wildcard allowed")
        @RequestParam(required = false) lastName: String?,
        @ApiParam("First name of the professional, '*' wildcard allowed")
        @RequestParam(required = false) firstName: String?,
        @ApiParam("Profession code, e.g. PHYSICIAN, PHYSIOTHERAPIST. Omit (or pass ALL) for every profession")
        @RequestParam(required = false) profession: String?,
        @ApiParam("INAMI/RIZIV number of the professional. Mutually exclusive with ssin")
        @RequestParam(required = false) nihii: String?,
        @ApiParam("NISS/INSZ of the professional. Mutually exclusive with nihii")
        @RequestParam(required = false) ssin: String?,
        @ApiParam("Zip code of the contact address. Mutually exclusive with city")
        @RequestParam(required = false) zipCode: String?,
        @ApiParam("City or village of the contact address. Mutually exclusive with zipCode")
        @RequestParam(required = false) city: String?,
        @ApiParam("E-mail address of the professional")
        @RequestParam(required = false) email: String?,
        @ApiParam("Index of the first result, defaults to 0. No total is returned: page until a page holds < limit")
        @RequestParam(required = false) offset: Int?,
        @ApiParam("Maximum number of results per page, defaults to and capped at 100 by the eHealth service")
        @RequestParam(required = false) limit: Int?
    ): List<HealthcareParty> = addressbookService.searchHcp(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        lastName = lastName,
        firstName = firstName,
        profession = profession,
        nihii = nihii,
        ssin = ssin,
        zipCode = zipCode,
        city = city,
        email = email,
        offset = offset ?: 0,
        limit = limit ?: 100
    )

    @ApiOperation(
        value = "Search healthcare organizations by name",
        notes = "Searches the eHealth address book for institutions whose name matches. The name accepts a '*' " +
            "wildcard (*clinique*). The institution type defaults to HOSPITAL when 'type' is omitted."
    )
    @GetMapping("/search/org/{name}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchOrg(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("Name of the institution, '*' wildcard allowed", required = true)
        @PathVariable name: String,
        @ApiParam("Type / quality of the institution, e.g. HOSPITAL. Defaults to HOSPITAL")
        @RequestParam(required = false) type: String?
    ): List<HealthcareParty> = addressbookService.searchOrg(
        keystoreId, tokenId, passPhrase, name, type ?: "HOSPITAL"
    )

    @ApiOperation(
        value = "Get the full contact information of a professional by INAMI/RIZIV number",
        notes = "Returns the professional with their addresses, telecoms, professionCodes and eHealthBox entries. " +
            "This is the call that completes a search result, which carries none of those."
    )
    @GetMapping("/hcp/nihii/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getHcpByNihii(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("INAMI/RIZIV number of the professional", required = true)
        @PathVariable nihii: String,
        @ApiParam("Language of the address descriptions (fr, nl, de). Defaults to fr")
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getHcp(
        keystoreId, tokenId, passPhrase, nihii, null, null, language ?: "fr"
    )

    @ApiOperation(
        value = "Get the full contact information of a professional by NISS/INSZ",
        notes = "Returns the professional with their addresses, telecoms, professionCodes and eHealthBox entries. " +
            "A person can hold several professions: 'quality' selects which one the nihii and addresses are taken " +
            "from, and defaults to PHYSICIAN."
    )
    @GetMapping("/hcp/ssin/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getHcpBySsin(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("NISS/INSZ of the professional", required = true)
        @PathVariable ssin: String,
        @ApiParam("Profession code selecting among the person's professions. Defaults to PHYSICIAN")
        @RequestParam(required = false) quality: String?,
        @ApiParam("Language of the address descriptions (fr, nl, de). Defaults to fr")
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getHcp(
        keystoreId, tokenId, passPhrase, null, ssin, quality, language ?: "fr"
    )

    @ApiOperation(
        value = "Get the full contact information of an organization by INAMI/RIZIV number",
        notes = "Returns the institution with its addresses, telecoms and eHealthBox entries."
    )
    @GetMapping("/org/nihii/{nihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getOrgByNihii(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("INAMI/RIZIV number of the institution", required = true)
        @PathVariable nihii: String,
        @ApiParam("Language of the name and address descriptions (fr, nl, de). Defaults to fr")
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, null, null, nihii, language ?: "fr"
    )

    @ApiOperation(
        value = "Get the full contact information of an organization by CBE/KBO number",
        notes = "Returns the institution with its addresses, telecoms and eHealthBox entries."
    )
    @GetMapping("/org/cbe/{cbe}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getOrgByCbe(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("CBE/KBO enterprise number of the institution", required = true)
        @PathVariable cbe: String?,
        @ApiParam("Language of the name and address descriptions (fr, nl, de). Defaults to fr")
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, null, cbe, null, language ?: "fr"
    )

    @ApiOperation(
        value = "Get the full contact information of an organization by EHP identifier",
        notes = "Returns the institution with its addresses, telecoms and eHealthBox entries."
    )
    @GetMapping("/org/ehp/{ehp}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getOrgByEhp(
        @ApiParam("The keystore ID for the certificate of the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @ApiParam("The token ID for the healthcare provider", required = true)
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @ApiParam("The passphrase for the keystore", required = true)
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @ApiParam("EHP identifier of the institution", required = true)
        @PathVariable ehp: String?,
        @ApiParam("Language of the name and address descriptions (fr, nl, de). Defaults to fr")
        @RequestParam(required = false) language: String?
    ): HealthcareParty? = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, ehp, null, null, language ?: "fr"
    )
}
