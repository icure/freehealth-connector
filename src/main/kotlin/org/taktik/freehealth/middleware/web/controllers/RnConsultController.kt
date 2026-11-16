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

import ma.glasnost.orika.MapperFacade
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonPhoneticallyResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultPersonMid
import org.taktik.freehealth.middleware.service.RnConsultService
import java.util.*

@RestController
@RequestMapping("/rnconsult")
class RnConsultController(val rnConsultService: RnConsultService, val mapper: MapperFacade){
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

    @GetMapping("/phonetically/{dateOfBirth}/{lastName}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchPersonPhonetically(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "dateOfBirth") dateOfBirth: Int,
        @PathVariable(value = "lastName") lastName: String,
        @RequestParam(required = false) firstName: String?,
        @RequestParam(required = false) middleName: String?,
        @ApiParam(value = "FIRST_LETTER_FIRST_GIVENNAME, COMPLETE_FIRST_GIVENNAME, ALL_GIVENNAME or IGNORE_GIVENNAME. Defaults to COMPLETE_FIRST_GIVENNAME, or IGNORE_GIVENNAME when no firstName is given") @RequestParam(required = false) matchingType: String?,
        @ApiParam(value = "MALE/M or FEMALE/F. Any other value, or no value, does not filter on gender") @RequestParam(required = false) gender: String?,
        @RequestParam(required = false) countryCode: Int?,
        @RequestParam(required = false) cityCode: String?,
        @ApiParam(value = "Tolerance on the date of birth. Defaults to 0") @RequestParam(required = false) tolerance: Int?,
        @ApiParam(value = "Maximum number of results. Defaults to 20") @RequestParam(required = false) limit: Int?
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
