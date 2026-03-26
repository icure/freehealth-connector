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

import jakarta.servlet.http.HttpServletRequest
import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonPhoneticallyResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultPersonMid
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.RnConsultService
import java.util.*

@RestController
@RequestMapping("/rnconsult")
@Tag(name = "RnConsult", description = "Consult the Belgian National Registry (v2) to search, verify, and register person identity information.")
class RnConsultController(val rnConsultService: RnConsultService, val mapper: MapperFacade){
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
    fun handleBadRequest(req: HttpServletRequest, ex: jakarta.xml.ws.soap.SOAPFaultException): String = ex.message ?: "unknown reason"

    @Operation(
        summary = "Search a person by SSIN",
        description = "Looks up a person's identity information in the Belgian National Registry using their SSIN (social security identification number)."
    )
    @GetMapping("/bySsin/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchPersonBySsin(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "ssin") ssin: String
    ): RnConsultSearchPersonBySsinResponseDto?{
        return rnConsultService.searchPersonBySsin(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            ssin = ssin
        )
     }

    @Operation(
        summary = "Search persons phonetically",
        description = "Performs a phonetic search in the Belgian National Registry using date of birth and last name, with optional filters for first name, gender, country, city, and tolerance."
    )
    @GetMapping("/phonetically/{dateOfBirth}/{lastName}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchPersonPhonetically(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "dateOfBirth") dateOfBirth: Int,
        @PathVariable(value = "lastName") lastName: String,
        @RequestParam(required = false) firstName: String?,
        @RequestParam(required = false) middleName: String?,
        @RequestParam(required = false) matchingType: String?,
        @RequestParam(required = false) gender: String?,
        @RequestParam(required = false) countryCode: Int?,
        @RequestParam(required = false) cityCode: String?,
        @RequestParam(required = false) tolerance: Int?,
        @RequestParam(required = false) limit: Int?
    ): RnConsultSearchPersonPhoneticallyResponseDto?{
        return rnConsultService.searchPersonPhonetically(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            dateOfBirth = dateOfBirth,
            lastName = lastName,
            firstName = firstName,
            middleName = middleName,
            matchingType = matchingType,
            gender = gender,
            countryCode = countryCode?:0,
            cityCode = cityCode,
            tolerance = tolerance,
            limit = limit
        )
    }

    @Operation(
        summary = "Register a person in the National Registry",
        description = "Registers a new person in the Belgian National Registry using the provided identity information."
    )
    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerPerson(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody mid: RnConsultPersonMid
    ) = rnConsultService.registerPerson(
        keystoreId,
        tokenId,
        passPhrase,
        mid
    )

    @Operation(
        summary = "Consult current SSIN history",
        description = "Retrieves the current SSIN and history of SSIN changes for a person from the Belgian National Registry."
    )
    @GetMapping("/history/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultCurrentSsin(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "ssin") ssin: String
    ) = rnConsultService.consultCurrentSsin(
        keystoreId,
        tokenId,
        passPhrase,
        ssin
    )

    @Operation(
        summary = "Verify a person's identity",
        description = "Verifies a person's identity in the Belgian National Registry using SSIN, card number, or barcode."
    )
    @GetMapping("/verifyId", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun verifyId(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam(required = false) ssin: String?,
        @RequestParam(required = false) cardNumber: String?,
        @RequestParam(required = false) barCoded: String?
    ) = rnConsultService.verifyId(
        keystoreId,
        tokenId,
        passPhrase,
        ssin,
        cardNumber,
        barCoded
    )

}
