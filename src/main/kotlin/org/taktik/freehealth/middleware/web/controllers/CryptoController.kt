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
import org.springframework.web.multipart.MultipartFile
import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.freehealth.middleware.service.CryptoService
import java.util.UUID

@RestController
@RequestMapping("/crypto")
@Tag(name = "Crypto", description = "Encryption and decryption service using eHealth certificates. Provides operations to encrypt data for a specific addressee or decrypt data using the keystore's private key.")
class CryptoController(val cryptoService: CryptoService) {
    @Operation(
        summary = "Encrypt data",
        description = "Encrypts raw binary data using the certificate associated with the specified identifier type and ID. The identifier type determines the addressing scheme (e.g., NIHII, SSIN, CBE) and the ID is the actual identifier value of the recipient."
    )
    @PostMapping("/encrypt/{identifier}/{id}", consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun encrypt(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "identifier") identifier: String,
        @PathVariable(value = "id") id: String,
        @RequestParam(value = "applicationId", required = false) applicationId: String?,
        @RequestBody plainData: ByteArray
               ): ByteArray? = cryptoService.encrypt(keystoreId, passPhrase, Addressee(IdentifierType.valueOf(identifier)).apply { this.id = id; this.applicationId = applicationId ?: "" }, plainData)


    @Operation(
        summary = "Encrypt a file",
        description = "Encrypts a file uploaded as multipart form data using the certificate associated with the specified identifier type and ID. This is the multipart equivalent of the binary encrypt endpoint."
    )
    @PostMapping("/encryptFile/{identifier}/{id}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun encryptFile(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "identifier") identifier: String,
        @PathVariable(value = "id") id: String,
        @RequestParam(value = "applicationId", required = false) applicationId: String?,
        @RequestParam plainData: MultipartFile
               ): ByteArray? = cryptoService.encrypt(keystoreId, passPhrase, Addressee(IdentifierType.valueOf(identifier)).apply { this.id = id; this.applicationId = applicationId ?: "" }, plainData.bytes)


    @Operation(
        summary = "Decrypt data",
        description = "Decrypts raw binary data using the private key from the keystore identified by the provided keystoreId and passPhrase."
    )
    @PostMapping("/decrypt", consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun decrypt(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody encryptedData: ByteArray
               ): ByteArray? = cryptoService.decrypt(keystoreId, passPhrase, encryptedData)

    @Operation(
        summary = "Decrypt a file",
        description = "Decrypts a file uploaded as multipart form data using the private key from the keystore identified by the provided keystoreId and passPhrase. This is the multipart equivalent of the binary decrypt endpoint."
    )
    @PostMapping("/decryptFile", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun decryptFile(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody encryptedData: MultipartFile
               ): ByteArray? = cryptoService.decrypt(keystoreId, passPhrase, encryptedData.bytes)
}
