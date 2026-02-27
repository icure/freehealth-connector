package org.taktik.freehealth.middleware.web.controllers

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
import org.taktik.freehealth.middleware.dto.common.GenAsyncResponse
import org.taktik.freehealth.middleware.dto.dmg.DmgConsultation
import org.taktik.freehealth.middleware.service.DmgService
import java.util.*

/**
 * REST controller for managing the Dossier Medical Global (DMG), also known as the Global Medical File (GMD).
 *
 * This controller exposes endpoints for healthcare providers (typically general practitioners) to interact
 * with the Belgian eHealth DMG services. Operations include registering as a GMD holder with an insurance
 * organization, consulting the GMD status for a patient, notifying DMG changes, requesting DMG lists,
 * retrieving asynchronous DMG messages, and confirming processed messages and acknowledgements.
 *
 * All endpoints require authentication via keystore, token, and passphrase headers.
 */
@RestController
@RequestMapping("/gmd")
@Tag(name = "DMG", description = "Global Medical File (DMG/GMD) management: register as GMD holder, consult GMD status, notify GMD changes, and retrieve DMG messages.")
class DmgController(val dmgService: DmgService, val mapper: MapperFacade) {
    /**
     * Registers a healthcare provider as a Global Medical File (GMD/DMG) holder with a specific
     * insurance organization. This registration includes the provider's bank account details (BIC/IBAN)
     * for payment of the GMD management fee.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param oa insurance organization code (organisme assureur)
     * @param bic BIC (Bank Identifier Code) for the GP's GMD registration
     * @param iban IBAN (International Bank Account Number) for the GP's GMD registration
     * @return the registration result from the eHealth DMG service
     */
    @Operation(
        summary = "Register as GMD holder",
        description = "Registers a healthcare provider as a Global Medical File (GMD/DMG) holder with a specific insurance organization (OA), including bank account details."
    )
    @PostMapping("/register/{oa}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerDoctor(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpFirstName: String, @RequestParam hcpLastName: String, @PathVariable oa: String, @RequestParam bic: String, @RequestParam iban: String) =
        dmgService.registerDoctor(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, oa = oa, bic = bic, iban = iban)

    /**
     * Consults the Global Medical File (DMG/GMD) status for a patient. Returns information about
     * the current GMD holder and registration details. The patient can be identified either by SSIN
     * or by their registration number with an insurance organization.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore (as String, converted internally)
     * @param tokenId UUID of the SAML authentication token (as String, converted internally)
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param patientSsin patient's SSIN (social security number), optional if regNrWithMut is provided
     * @param patientGender patient's gender, optional
     * @param oa insurance organization code (organisme assureur), optional
     * @param regNrWithMut patient's registration number with the insurance organization, optional
     * @param requestDate date of the request as epoch milliseconds; defaults to the current date if not provided
     * @return a [DmgConsultation] containing the GMD status, holder information, and registration details
     */
    @Operation(
        summary = "Consult DMG status",
        description = "Consults the Global Medical File (DMG/GMD) status for a patient, returning information about the current GMD holder and registration details."
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultDmg(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: String,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientGender: String?,
        @RequestParam(required = false) oa: String?,
        @RequestParam(required = false) regNrWithMut: String?,
        @RequestParam(required = false) requestDate: Long?
    ): DmgConsultation =
        dmgService.consultDmg(keystoreId = UUID.fromString(keystoreId), tokenId = UUID.fromString(tokenId), passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, patientSsin = patientSsin, patientGender = patientGender, oa = oa, regNrWithMut = regNrWithMut, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date()).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgConsultation::class.java)
        }

    /**
     * Sends a DMG notification for a patient, informing the insurance organization of a Global Medical
     * File change using the specified INAMI/RIZIV nomenclature code. This is used when a GP takes over
     * or relinquishes the GMD for a patient.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param nomenclature INAMI/RIZIV nomenclature code for the medical act triggering the notification
     * @param patientSsin patient's SSIN (social security number), optional if regNrWithMut is provided
     * @param oa insurance organization code (organisme assureur), optional
     * @param regNrWithMut patient's registration number with the insurance organization, optional
     * @param patientFirstName patient's first name, optional
     * @param patientLastName patient's last name, optional
     * @param patientGender patient's gender, optional
     * @param requestDate date of the request as epoch milliseconds; defaults to the current date if not provided
     * @param traineeSupervisorSsin SSIN of the trainee supervisor, optional (used when a trainee performs the act)
     * @param traineeSupervisorNihii NIHII of the trainee supervisor, optional
     * @param traineeSupervisorFirstName first name of the trainee supervisor, optional
     * @param traineeSupervisorLastName last name of the trainee supervisor, optional
     * @return a DmgNotification containing the result of the notification request
     */
    @Operation(
        summary = "Notify DMG change",
        description = "Sends a DMG notification for a patient using the specified nomenclature code, informing the insurance organization of a Global Medical File change."
    )
    @PostMapping("/notify/{nomenclature}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun notifyDmg(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable nomenclature: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) oa: String?,
        @RequestParam(required = false) regNrWithMut: String?,
        @RequestParam(required = false) patientFirstName: String?,
        @RequestParam(required = false) patientLastName: String?,
        @RequestParam(required = false) patientGender: String?,
        @RequestParam(required = false) requestDate: Long?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?

        ) =
        dmgService.notifyDmg(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, traineeSupervisorFirstName = traineeSupervisorFirstName, traineeSupervisorLastName = traineeSupervisorLastName, traineeSupervisorSsin = traineeSupervisorSsin, traineeSupervisorNihii = traineeSupervisorNihii, patientSsin = patientSsin, patientGender = patientGender, patientFirstName = patientFirstName ?: "", patientLastName = patientLastName ?: "", oa = oa, regNrWithMut = regNrWithMut, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date(), nomenclature = nomenclature).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgNotification::class.java)
        }

    /**
     * Submits an asynchronous request to retrieve the list of patients for whom the healthcare provider
     * holds a Global Medical File (DMG/GMD). The response contains a reference that can be used later
     * to retrieve the actual list via [getDmgMessages].
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param oa insurance organization code (organisme assureur) to query, optional (queries all OAs if not specified)
     * @param requestDate date of the request as epoch milliseconds; defaults to the current date if not provided
     * @return a [GenAsyncResponse] containing the reference for the asynchronous request
     */
    @Operation(
        summary = "Request DMG list",
        description = "Submits an asynchronous request to retrieve the list of patients for whom the healthcare provider holds a Global Medical File (DMG/GMD)."
    )
    @PostMapping("/reqlist", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun postDmgsListRequest(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam(required = false) oa: String?,
        @RequestParam(required = false) requestDate: Long?
    ) =
        dmgService.postDmgsListRequest(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, oa = oa, requestDate = requestDate?.let { Date(requestDate) }
            ?: Date()).let { mapper.map(it, GenAsyncResponse::class.java)}

    /**
     * Retrieves DMG messages (responses to previous asynchronous requests) by their message names.
     * These messages contain the results of operations such as DMG list requests, and include
     * the list of DMG registrations held by the healthcare provider.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param messageNames list of message names (references) to retrieve; null retrieves all pending messages
     * @return a DmgsList containing the retrieved DMG registrations and associated information
     */
    @Operation(
        summary = "Retrieve DMG messages",
        description = "Retrieves DMG messages (responses to previous asynchronous requests) by their message names, returning the list of DMG registrations."
    )
    @PostMapping("/messages", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getDmgMessages(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestBody messageNames: List<String>?) =
        dmgService.getDmgMessages(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, messageNames = messageNames).let {
            mapper.map(it, org.taktik.freehealth.middleware.dto.dmg.DmgsList::class.java)
        }

    /**
     * Confirms the successful processing of DMG messages by submitting their hashes. Once confirmed,
     * these messages will not be returned in subsequent calls to [getDmgMessages]. This is part of the
     * asynchronous message processing workflow to ensure reliable message delivery.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param dmgMessagesHashes list of hashes identifying the DMG messages to confirm as processed
     * @return the confirmation result from the eHealth DMG service
     */
    @Operation(
        summary = "Confirm DMG messages",
        description = "Confirms the successful processing of DMG messages by submitting their hashes, preventing them from being returned in subsequent retrievals."
    )
    @PostMapping("/confirm/messages", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmDmgMessages(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestBody dmgMessagesHashes: List<String>) =
        dmgService.confirmDmgMessages(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, dmgMessagesHashes = dmgMessagesHashes)

    /**
     * Confirms the successful processing of DMG acknowledgement messages by submitting their hashes.
     * Acknowledgements are system-level confirmations from the eHealth platform that prior DMG operations
     * (such as notifications or registrations) were received. Confirming them prevents redelivery.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number (unique Belgian healthcare provider identifier)
     * @param hcpSsin healthcare provider's SSIN (social security number)
     * @param hcpFirstName healthcare provider's first name
     * @param hcpLastName healthcare provider's last name
     * @param dmgAcksHashes list of hashes identifying the DMG acknowledgement messages to confirm as processed
     * @return the confirmation result from the eHealth DMG service
     */
    @Operation(
        summary = "Confirm DMG acknowledgements",
        description = "Confirms the successful processing of DMG acknowledgement messages by submitting their hashes."
    )
    @PostMapping("/confirm/acks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun confirmAcks(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestBody dmgAcksHashes: List<String>) =
        dmgService.confirmAcks(keystoreId = keystoreId, tokenId = tokenId, passPhrase = passPhrase, hcpNihii = hcpNihii, hcpSsin = hcpSsin, hcpFirstName = hcpFirstName, hcpLastName = hcpLastName, dmgAcksHashes = dmgAcksHashes)
}
