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
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import org.taktik.freehealth.middleware.dto.mhm.CancelSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.EndSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.StartSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EattestV2Service
import org.taktik.freehealth.middleware.service.MhmService
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import jakarta.servlet.http.HttpServletRequest

/**
 * REST controller for Medical House Management (MHM) operations.
 *
 * Medical houses (maisons medicales) use a capitation-based payment model where patients
 * subscribe to receive care. This controller provides endpoints to manage patient subscriptions:
 * starting new subscriptions, cancelling existing ones, and notifying about subscription closures.
 * All operations communicate with the MyCarenet platform.
 */
@RestController
@RequestMapping("/mhm")
@Tag(name = "MHM", description = "Medical House Management. Manages registration and administration for medical houses (maisons medicales), including subscription start, cancellation, and closure notifications.")
class MhmController(val mhmService: MhmService) {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"

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


    /**
     * Registers a new patient subscription to a medical house (maison medicale).
     *
     * Sends a subscription request to the MyCarenet platform with the patient's details,
     * subscription start date, and signature type. The patient can be identified either by
     * their SSIN or by their insurance organization (IO) and membership number.
     * Supports trial subscriptions and recovery mode for resubmitting previously failed requests.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number of the medical house
     * @param hcpName name of the medical house
     * @param patientFirstName first name of the patient being subscribed
     * @param patientLastName last name of the patient being subscribed
     * @param patientGender gender of the patient (e.g., "male", "female")
     * @param startDate subscription start date in YYYYMMDD integer format
     * @param signatureType type of signature used for the subscription request
     * @param isTrial optional flag indicating whether this is a trial subscription; defaults to false
     * @param patientSsin optional social security number of the patient
     * @param io optional insurance organization code for the patient
     * @param ioMembership optional membership number within the insurance organization
     * @param isRecovery optional flag indicating recovery mode for resubmission; defaults to false
     * @param isTestForNotify optional flag for testing notification flow; defaults to false
     * @return a [StartSubscriptionResultWithResponse] containing the subscription result and raw response, or null on failure
     */
    @Operation(
        summary = "Send a medical house subscription",
        description = "Registers a new patient subscription to a medical house (maison medicale). Sends the subscription request to the MyCarenet platform with patient details, start date, and signature type. Supports trial subscriptions and recovery mode."
    )
    @PostMapping("/sendSubscription", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendSubscription(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam startDate: Int,
        @RequestParam signatureType: String,
        @RequestParam(required = false) isTrial: Boolean?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) io: String?,
        @RequestParam(required = false) ioMembership: String?,
        @RequestParam(required = false) isRecovery: Boolean?,
        @RequestParam(required = false) isTestForNotify: Boolean?
    ) : StartSubscriptionResultWithResponse? {
        return mhmService.sendSubscription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpName = hcpName,
            patientSsin = patientSsin,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            io = io,
            ioMembership = ioMembership,
            startDate = startDate,
            isTrial = isTrial ?: false,
            signatureType = signatureType,
            isRecovery = isRecovery ?: false,
            isTestForNotify = isTestForNotify ?: false)
    }

    /**
     * Cancels an existing patient subscription to a medical house.
     *
     * The subscription to cancel is identified by its reference number. The patient can be
     * identified either by their SSIN or by their insurance organization (IO) and membership number.
     *
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param tokenId UUID of the SAML authentication token
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpNihii NIHII number of the medical house
     * @param hcpName name of the medical house
     * @param patientFirstName first name of the patient
     * @param patientLastName last name of the patient
     * @param patientGender gender of the patient (e.g., "male", "female")
     * @param reference reference number of the subscription to cancel
     * @param patientSsin optional social security number of the patient
     * @param io optional insurance organization code for the patient
     * @param ioMembership optional membership number within the insurance organization
     * @return a [CancelSubscriptionResultWithResponse] containing the cancellation result and raw response, or null on failure
     */
    @Operation(
        summary = "Cancel a medical house subscription",
        description = "Cancels an existing patient subscription to a medical house. The subscription is identified by its reference number. Patient identification can be done via SSIN or via insurance organization (IO) and membership number."
    )
    @PostMapping("/cancelSubscription", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun cancelSubscription(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam reference: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) io: String?,
        @RequestParam(required = false) ioMembership: String?
    ) : CancelSubscriptionResultWithResponse? {
      return mhmService.cancelSubscription(
          keystoreId = keystoreId,
          tokenId = tokenId,
          passPhrase = passPhrase,
          hcpNihii = hcpNihii,
          hcpName = hcpName,
          patientSsin = patientSsin,
          patientFirstName = patientFirstName,
          patientLastName = patientLastName,
          patientGender = patientGender,
          io = io,
          ioMembership = ioMembership,
          reference = reference
      )
    }

    @Operation(
        summary = "Notify closure of a medical house subscription",
        description = "Sends a notification about the closure of an existing patient subscription to a medical house. Requires the subscription reference, end date, reason for closure, and the decision type. Used to formally end a patient's registration with a medical house."
    )
    @PostMapping("/notifySubscriptionClosure", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun notifySubscriptionClosure(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam reference: String,
        @RequestParam endDate: Int,
        @RequestParam reason: String,
        @RequestParam decisionType : String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) io: String?,
        @RequestParam(required = false) ioMembership: String?
    ): EndSubscriptionResultWithResponse? {
      return mhmService.notifySubscriptionClosure(
          keystoreId = keystoreId,
          tokenId = tokenId,
          passPhrase = passPhrase,
          hcpNihii = hcpNihii,
          hcpName = hcpName,
          patientSsin = patientSsin,
          patientFirstName = patientFirstName,
          patientLastName = patientLastName,
          patientGender = patientGender,
          io = io,
          ioMembership = ioMembership,
          reference = reference,
          endDate = endDate,
          reason = reason,
          decisionType = decisionType
      )
    }
}
