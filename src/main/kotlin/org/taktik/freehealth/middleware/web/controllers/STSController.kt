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

import com.nimbusds.oauth2.sdk.TokenResponse
import org.apache.log4j.MDC
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.taktik.freehealth.middleware.domain.sts.BearerToken
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.dto.CertificateInfo
import org.taktik.freehealth.middleware.dto.MergeKeystoresRequestBody
import org.taktik.freehealth.middleware.dto.MergeKeystoresResponseDto
import org.taktik.freehealth.middleware.dto.UUIDType
import org.taktik.freehealth.middleware.service.SSOService
import org.taktik.freehealth.middleware.service.STSService
import java.util.UUID

@RestController
@RequestMapping("/sts")
class STSController(private val stsService: STSService, private val ssoService: SSOService) {
    val log = LoggerFactory.getLogger(this.javaClass)

    @ApiOperation(
        value = "Upload a keystore file",
        notes = "Uploads a keystore file to the STS service and returns the UUID of the uploaded keystore. The kestore file must be in PKCS12 format and should contain the private key and certificate chain for the user. It will never be stored anywhere but encrypted in RAM"
    )
    @PostMapping("/keystore", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun uploadKeystore(@RequestParam file: MultipartFile, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String): UUIDType {
        MDC.put("company", company)
        val uuidTypeResponse = UUIDType(stsService.uploadKeystore(file))
        MDC.clear()
        return uuidTypeResponse
    }

    @GetMapping("/keystore/{keystoreId}/info/{quality}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getKeystoreInfo(@PathVariable(name = "keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,  @PathVariable(name = "quality") quality: String): CertificateInfo {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val keystoreInfoResponse = stsService.getKeystoreInfo(keystoreId, passPhrase, quality)
        MDC.clear()
        return keystoreInfoResponse
    }

    @Deprecated("Please specify a quality")
    @GetMapping("/token", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun requestToken(@RequestHeader(name = "X-FHC-passPhrase") passPhrase: String?, @RequestParam ssin: String, @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String, @RequestParam(required = false) isMedicalHouse: Boolean?, @RequestParam(required = false) isGuardPost: Boolean?, @RequestParam(required = false) isSortingCenter: Boolean?, @RequestHeader(name = "X-FHC-tokenId", required = false) previousTokenId: UUID?): SamlTokenResult? {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val requestTokenResponse = stsService.requestToken(keystoreId, ssin, passPhrase ?: "", when {
            isMedicalHouse ?: false -> "medicalhouse"
            isGuardPost ?: false -> "guardpost"
            isSortingCenter ?: false -> "sortingcenter"
            else -> "doctor"
        }, previousTokenId)
        MDC.clear()
        return requestTokenResponse
    }

    @ApiOperation(
        value = "Request a token",
        notes = "Requests a token for the user identified by the given SSIN and keystoreId. The passPhrase is used to decrypt the keystore file. The quality parameter specifies the type of token to request (e.g., doctor, medicalhouse, guardpost, sortingcenter). If a previousTokenId is provided, it will be used to renew the token."
    )
    @GetMapping("/token/{quality}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun requestToken(@RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam ssin: String, @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String, @PathVariable(name = "quality") quality: String, @RequestHeader(name = "X-FHC-tokenId", required = false) previousTokenId: UUID?): SamlTokenResult? {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val requestTokenResponse = stsService.requestToken(keystoreId, ssin, passPhrase, quality ?: "doctor", previousTokenId)
        MDC.clear()
        return requestTokenResponse
    }

    @GetMapping("/token/{quality}/{cbeNumber}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun requestTokenInstitutions(@RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam ssin: String, @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String, @PathVariable(name = "quality") quality: String, @PathVariable(name = "cbeNumber") cbeNumber: String, @RequestHeader(name = "X-FHC-tokenId", required = false) previousTokenId: UUID?): SamlTokenResult? {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val requestTokenResponse = stsService.requestToken(keystoreId, ssin, passPhrase, quality, previousTokenId, cbeNumber)
        MDC.clear()
        return requestTokenResponse
    }

    @ApiOperation(
        value = "Register a SAML token obtained externally",
        notes = "Allows the registration of a SAML token obtained from an external source. The tokenId is used to identify the token, and the quality parameter specifies the type of token (e.g., doctor). If no quality is specified, it defaults to 'doctor'. This endpoint is useful for integrating with external systems that provide SAML tokens."
    )
    @PostMapping("/token", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerToken(@RequestBody token: String, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam(required = false) quality: String?, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String) : Boolean{
        MDC.put("company", company)
        val registerTokenResponse = stsService.registerToken(tokenId, token, quality ?: "doctor")
        MDC.clear()
        return registerTokenResponse
    }

    @ApiOperation(
        value = "Get a SAML token",
        notes = "Retrieves a SAML token for the user identified by the given keystoreId and passPhrase. The tokenId is used to identify the token. This endpoint is useful for obtaining a SAML token that can be used for authentication and authorization in other systems."
    )
    @GetMapping("/keystore/check", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun checkKeystoreExist(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String): Boolean {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val checkIfKeyStoreExistsResponse = stsService.checkIfKeystoreExist(keystoreId)
        MDC.clear()
        return checkIfKeyStoreExistsResponse
    }

    @ApiOperation(
        value = "Check a SAML token validity",
        notes = "Check that a cached SAML token is still valid. The tokenId is used to identify the token. This endpoint is useful for verifying the validity of a cached SAML token before using it for authentication or authorization."
    )
    @GetMapping("/token/check", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun checkTokenValid(@RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String): Boolean {
        MDC.put("company", company)
        val checkTokenValidResponse = stsService.checkTokenValid(tokenId)
        MDC.clear()
        return checkTokenValidResponse
    }

    @ApiOperation(
        value = "Get a Bearer token from a SAML token",
        notes = "Uses a previously obtained SAML token to get a Bearer token. The tokenId is used to identify the SAML token, and the passPhrase is used to decrypt the keystore file. The ssin is the social security number of the user, and the keystoreId is the UUID of the keystore file. This endpoint is useful for obtaining a Bearer token that can be used for API authentication."
    )
    @GetMapping("/token/bearer", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getBearerToken(@RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam ssin: String, @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String): BearerToken? {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val bearerTokenResponse = ssoService.getBearerToken(tokenId, keystoreId, passPhrase)
        MDC.clear()
        return bearerTokenResponse
    }

    @ApiOperation(
        value = "Get a OAuth2 token from a SAML token",
        notes = "Uses a previously obtained SAML token to get an OAuth2 token. The tokenId is used to identify the SAML token, and the passPhrase is used to decrypt the keystore file. The cbe (Common Business Entity) and kid (Key Identifier) are used to specify the OAuth2 token request. This endpoint is useful for obtaining an OAuth2 token that can be used for API authentication."
    )
    @GetMapping("/token/oauth2/{cbe}/{kid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getOauth2Token(
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String,
        @PathVariable(name = "cbe") cbe: String,
        @PathVariable(name = "kid") kid: String
    ): TokenResponse {
        MDC.put("keystoreId", keystoreId)
        MDC.put("company", company)
        val oauth2TokenResponse = ssoService.getOauth2Token(tokenId, keystoreId, passPhrase, cbe, kid)
        MDC.clear()
        return oauth2TokenResponse
    }

    @PostMapping("/keystore/merge", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun mergeKeystores(@RequestBody request: MergeKeystoresRequestBody, @RequestHeader(name = "X-Company", required = false, defaultValue = "NA") company: String): MergeKeystoresResponseDto {
        MDC.put("company", company)
        val mergeKeystoresResponse = stsService.mergeKeystores(request.newKeystore, request.oldKeystore, request.newPassword, request.oldPassword)
        MDC.clear()
        return mergeKeystoresResponse
    }

}
