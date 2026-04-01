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
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.domain.chapter4.Appendix
import org.taktik.connector.business.domain.chapter4.RequestType
import org.taktik.freehealth.middleware.drugs.civics.AddedDocumentPreview
import org.taktik.freehealth.middleware.drugs.civics.ParagraphInfos
import org.taktik.freehealth.middleware.drugs.civics.ParagraphPreview
import org.taktik.freehealth.middleware.drugs.dto.MppPreview
import org.taktik.freehealth.middleware.service.Chapter4Service
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.io.IOUtils
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ResponseBody
import java.net.URL

/**
 * REST controller for Belgian Chapter IV prior authorization operations.
 *
 * Chapter IV of the Belgian INAMI/RIZIV nomenclature governs the reimbursement of special
 * or expensive medications that require prior authorization from the patient's insurance
 * organization (mutuality). This controller exposes endpoints to consult, request, cancel,
 * and close Chapter IV agreement requests via the eHealth platform.
 *
 * All endpoints require a valid PKCS12 keystore and SAML token, provided via HTTP headers.
 */
@RestController
@RequestMapping("/chap4")
@Tag(name = "Chapter4", description = "Belgian Chapter IV prior authorization for special medications requiring approval from insurance organizations.")
class Chapter4Controller(private val chapter4Service: Chapter4Service) {
    val log = LoggerFactory.getLogger(this .javaClass)

    /**
     * Retrieves existing Chapter IV agreement requests for a given patient. The results can
     * optionally be filtered by CIVICS paragraph, date range, and agreement reference.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpSsin healthcare provider's SSIN (social security identification number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientSsin patient's social security identification number
     * @param patientDateOfBirth patient's date of birth as epoch milliseconds
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param civicsVersion optional CIVICS version to use for the consultation
     * @param paragraph optional Chapter IV paragraph number to filter by
     * @param start optional start of the date range filter as epoch milliseconds; defaults to 15 days ago
     * @param end optional end of the date range filter as epoch milliseconds
     * @param reference optional agreement reference to filter by
     * @return the Chapter IV agreement consultation response containing matching agreement requests
     */
    @Operation(
        summary = "Consult agreement requests",
        description = "Retrieves existing Chapter IV agreement requests for a given patient, optionally filtered by paragraph, date range, and reference."
    )
    @GetMapping("/consult/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun agreementRequestsConsultation(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable patientSsin: String,
        @RequestParam patientDateOfBirth: Long,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) civicsVersion: String?,
        @RequestParam(required = false) paragraph: String?,
        @RequestParam(required = false) start: Long?,
        @RequestParam(required = false) end: Long?,
        @RequestParam(required = false) reference: String?) = chapter4Service.agreementRequestsConsultation(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        patientSsin = patientSsin,
        patientDateOfBirth = patientDateOfBirth,
        patientFirstName = patientFirstName,
        patientLastName = patientLastName,
        patientGender = patientGender,
        civicsVersion = civicsVersion,
        paragraph = paragraph,
        start = start ?: LocalDate.now().minusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        end = end,
        reference = reference)

    /**
     * Sends a new Chapter IV prior authorization request to the insurance organization for a
     * specific CIVICS paragraph and medication. The request includes patient demographics,
     * the targeted paragraph and verses, and any supporting appendices (clinical justification
     * documents).
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpSsin healthcare provider's SSIN
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientSsin patient's social security identification number
     * @param patientDateOfBirth patient's date of birth as epoch milliseconds
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param requestType the type of agreement request (e.g. "newrequest", "extension", "noncontinuousextension")
     * @param civicsVersion CIVICS version identifying the drug reimbursement context
     * @param paragraph Chapter IV paragraph number for which authorization is requested
     * @param verses optional comma-separated list of verse numbers within the paragraph
     * @param incomplete optional flag indicating whether the request is submitted as incomplete
     * @param start optional start date of the requested agreement period as epoch milliseconds; defaults to 15 days ago
     * @param end optional end date of the requested agreement period as epoch milliseconds
     * @param decisionReference optional reference to a previous decision by the insurance organization
     * @param ioRequestReference optional reference assigned by the insurance organization to a prior request
     * @param appendices list of supporting documents (clinical justifications, reports) for the authorization request
     * @return the Chapter IV agreement response from the insurance organization
     */
    @Operation(
        summary = "Request a new agreement",
        description = "Sends a new Chapter IV prior authorization request to the insurance organization for a specific paragraph and medication."
    )
    @PostMapping("/new/{patientSsin}/{civicsVersion}/{requestType}/{paragraph}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun requestAgreement(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
                         @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
                         @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
                         @RequestParam hcpNihii: String,
                         @RequestParam hcpSsin: String,
                         @RequestParam hcpFirstName: String,
                         @RequestParam hcpLastName: String,
                         @PathVariable patientSsin: String,
                         @RequestParam patientDateOfBirth: Long,
                         @RequestParam patientFirstName: String,
                         @RequestParam patientLastName: String,
                         @RequestParam patientGender: String,
                         @PathVariable requestType: String,
                         @PathVariable civicsVersion: String,
                         @PathVariable paragraph: String,
                         @RequestParam(required = false) verses: String?,
                         @RequestParam(required = false) incomplete: Boolean?,
                         @RequestParam(required = false) start: Long?,
                         @RequestParam(required = false) end: Long?,
                         @RequestParam(required = false) decisionReference: String?,
                         @RequestParam(required = false) ioRequestReference: String?,
                         @RequestBody appendices: List<Appendix>
                        ) =
        chapter4Service.requestAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            patientSsin = patientSsin,
            patientDateOfBirth = patientDateOfBirth,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            requestType = RequestType.valueOf(requestType),
            civicsVersion = civicsVersion,
            paragraph = paragraph,
            verses = verses?.split(",") ?: listOf(),
            incomplete = incomplete ?: false,
            start = start
                ?: LocalDate.now().minusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            end = end,
            decisionReference = decisionReference,
            ioRequestReference = ioRequestReference,
            appendices = appendices)

    /**
     * Cancels an existing Chapter IV agreement request. The agreement to cancel is identified
     * by either the decision reference or the insurance organization request reference.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpSsin healthcare provider's SSIN
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientSsin patient's social security identification number
     * @param patientDateOfBirth patient's date of birth as epoch milliseconds
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param decisionReference optional reference to the decision issued by the insurance organization
     * @param iorequestReference optional reference assigned by the insurance organization to the original request
     * @return the Chapter IV cancellation response from the insurance organization
     */
    @Operation(
        summary = "Cancel an agreement",
        description = "Cancels an existing Chapter IV agreement request identified by decision reference or IO request reference."
    )
    @DeleteMapping("/cancel/{patientSsin}")
    fun cancelAgreement(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
                        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
                        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
                        @RequestParam hcpNihii: String,
                        @RequestParam hcpSsin: String,
                        @RequestParam hcpFirstName: String,
                        @RequestParam hcpLastName: String,
                        @PathVariable patientSsin: String,
                        @RequestParam patientDateOfBirth: Long,
                        @RequestParam patientFirstName: String,
                        @RequestParam patientLastName: String,
                        @RequestParam patientGender: String,
                        @RequestParam(required = false) decisionReference: String?,
                        @RequestParam(required = false) iorequestReference: String?) =
        chapter4Service.cancelAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            passPhrase = passPhrase,
            patientSsin = patientSsin,
            patientDateOfBirth = patientDateOfBirth,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            decisionReference = decisionReference,
            iorequestReference = iorequestReference
                                       )

    /**
     * Closes an approved Chapter IV agreement, ending the authorization period. This is used
     * when the treatment covered by the agreement is no longer needed or has been completed.
     * The agreement is identified by its decision reference.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier) of the requesting provider
     * @param hcpSsin healthcare provider's SSIN
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientSsin patient's social security identification number
     * @param patientDateOfBirth patient's date of birth as epoch milliseconds
     * @param patientFirstName patient's first name
     * @param patientLastName patient's last name
     * @param patientGender patient's gender (e.g. "male", "female")
     * @param decisionReference reference to the decision issued by the insurance organization identifying the agreement to close
     * @return the Chapter IV close response from the insurance organization
     */
    @Operation(
        summary = "Close an agreement",
        description = "Closes an approved Chapter IV agreement using its decision reference, ending the authorization period."
    )
    @DeleteMapping("/close/{patientSsin}")
    fun closeAgreement(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
                       @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
                       @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
                       @RequestParam hcpNihii: String,
                       @RequestParam hcpSsin: String,
                       @RequestParam hcpFirstName: String,
                       @RequestParam hcpLastName: String,
                       @PathVariable patientSsin: String,
                       @RequestParam patientDateOfBirth: Long,
                       @RequestParam patientFirstName: String,
                       @RequestParam patientLastName: String,
                       @RequestParam patientGender: String,
                       @RequestParam decisionReference: String) =
        chapter4Service.closeAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            passPhrase = passPhrase,
            patientSsin = patientSsin,
            patientDateOfBirth = patientDateOfBirth,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            decisionReference = decisionReference
                                      )
}
