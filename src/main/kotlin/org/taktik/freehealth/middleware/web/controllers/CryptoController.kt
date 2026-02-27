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

/**
 * REST controller for encryption and decryption operations using Belgian eHealth certificates.
 *
 * Provides endpoints to encrypt data for a specific addressee (identified by their eHealth identifier
 * such as NIHII, SSIN, CBE, or EHP) or decrypt data using the private key stored in an uploaded
 * PKCS12 keystore. Supports both raw binary payloads and multipart file uploads.
 */
@RestController
@RequestMapping("/crypto")
@Tag(name = "Crypto", description = "Encryption and decryption service using eHealth certificates. Provides operations to encrypt data for a specific addressee or decrypt data using the keystore's private key.")
class CryptoController(val cryptoService: CryptoService) {
    /**
     * Encrypts raw binary data for a specific addressee identified by their eHealth identifier.
     *
     * The addressee is resolved using the identifier type (e.g., NIHII, SSIN, CBE, EHP) and the
     * corresponding identifier value. The encryption uses the addressee's eHealth certificate.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param identifier type of identifier (CBE, EHP, NIHII, SSIN) for the addressee
     * @param id the actual identifier value of the addressee
     * @param applicationId optional application identifier for the addressee
     * @param plainData raw binary data to encrypt
     * @return the encrypted binary data, or null if encryption fails
     */
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


    /**
     * Encrypts a file uploaded as multipart form data for a specific addressee.
     *
     * This is the multipart equivalent of the binary [encrypt] endpoint. The addressee is resolved
     * using the identifier type and value, and encryption uses the addressee's eHealth certificate.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param identifier type of identifier (CBE, EHP, NIHII, SSIN) for the addressee
     * @param id the actual identifier value of the addressee
     * @param applicationId optional application identifier for the addressee
     * @param plainData the file to encrypt, uploaded as multipart form data
     * @return the encrypted binary data, or null if encryption fails
     */
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


    /**
     * Decrypts raw binary data using the private key from the uploaded keystore.
     *
     * Uses the private key contained in the keystore identified by [keystoreId] and unlocked
     * with [passPhrase] to decrypt the provided encrypted binary payload.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param encryptedData encrypted binary data to decrypt
     * @return the decrypted binary data, or null if decryption fails
     */
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

    /**
     * Decrypts a file uploaded as multipart form data using the private key from the uploaded keystore.
     *
     * This is the multipart equivalent of the binary [decrypt] endpoint. Uses the private key
     * contained in the keystore identified by [keystoreId] and unlocked with [passPhrase].
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param encryptedData the encrypted file to decrypt, uploaded as multipart form data
     * @return the decrypted binary data, or null if decryption fails
     */
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
