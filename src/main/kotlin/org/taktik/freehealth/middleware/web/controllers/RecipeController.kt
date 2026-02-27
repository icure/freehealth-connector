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

import be.fgov.ehealth.recipe.protocol.v4.ListPrescriptionsResult
import be.recipe.services.core.PrescriptionStatus
import be.recipe.services.core.VisionOtherPrescribers
import be.recipe.services.prescriber.PutVisionResult
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeKmehrmessageType
import org.taktik.freehealth.middleware.domain.recipe.Feedback
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.domain.recipe.PrescriptionFullWithFeedback
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.recipe.ListStructuredPrescriptionsResult
import org.taktik.freehealth.middleware.dto.recipe.PrescriptionRequest
import org.taktik.freehealth.middleware.dto.recipe.PrescriptionsRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.service.RecipeV4Service
import org.taktik.freehealth.utils.FuzzyValues
import java.util.*

@RestController
@RequestMapping("/recipe")
@Tag(name = "Recipe", description = "Belgian electronic prescription (Recipe) services for creating, retrieving, revoking, and managing e-prescriptions via the Recipe v4 API.")
class RecipeController(val recipeV4Service: RecipeV4Service) {
    @Operation(
        summary = "Create an electronic prescription",
        description = "Creates a new electronic prescription in the Belgian Recipe system. The prescription content (patient, medications, HCP info) is provided in the request body. Returns the created prescription with its Recipe ID (RID)."
    )
    @Suppress("DuplicatedCode")
    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createPrescription(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestBody prescription: PrescriptionRequest,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
        ): Prescription =
        recipeV4Service.createPrescription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            patient = prescription.patient!!,
            hcp = prescription.hcp!!,
            feedback = prescription.feedback!!,
            medications = prescription.medications!!,
            prescriptionType = prescription.prescriptionType,
            notification = prescription.notification,
            executorId = prescription.executorId,
            samVersion = prescription.samVersion,
            deliveryDate = prescription.deliveryDate?.let {FuzzyValues.getLocalDateTime(it)},
            vendorName = prescription.vendorName ?: vendorName,
            packageName = prescription.packageName ?: packageVersion,
            packageVersion = prescription.packageVersion,
            vendorEmail = prescription.vendorEmail,
            vendorPhone = prescription.vendorPhone,
            vision = prescription.vision,
            visionOthers = prescription.visionOthers?. let { VisionOtherPrescribers.fromValue(it) },
            expirationDate = prescription.expirationDate?.let {FuzzyValues.getLocalDateTime(it)},
            lang = prescription.lang
        )

    @Operation(
        summary = "Create an electronic prescription (v4 explicit)",
        description = "Creates a new electronic prescription using the Recipe v4 API explicitly. Functionally identical to the root POST endpoint but uses the /v4 path for clarity. Returns the created prescription with its Recipe ID (RID)."
    )
    @Suppress("DuplicatedCode")
    @PostMapping("/v4", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createPrescriptionV4(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestBody prescription: PrescriptionRequest,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ): Prescription =
        recipeV4Service.createPrescription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            patient = prescription.patient!!,
            hcp = prescription.hcp!!,
            feedback = prescription.feedback!!,
            medications = prescription.medications!!,
            prescriptionType = prescription.prescriptionType,
            notification = prescription.notification,
            executorId = prescription.executorId,
            samVersion = prescription.samVersion,
            deliveryDate = prescription.deliveryDate?.let {FuzzyValues.getLocalDateTime(it)},
            vendorName = prescription.vendorName ?: vendorName,
            packageName = prescription.packageName ?: packageVersion,
            packageVersion = prescription.packageVersion,
            vendorEmail = prescription.vendorEmail,
            vendorPhone = prescription.vendorPhone,
            vision = prescription.vision,
            visionOthers = prescription.visionOthers?. let { VisionOtherPrescribers.fromValue(it) },
            expirationDate = prescription.expirationDate?.let {FuzzyValues.getLocalDateTime(it)},
            lang = prescription.lang
        )

    @Operation(
        summary = "Create multiple electronic prescriptions in batch",
        description = "Creates multiple electronic prescriptions in a single batch request via the Belgian Recipe system. Each medication batch results in a separate prescription. Returns a list of created prescriptions with their Recipe IDs (RIDs)."
    )
    @PostMapping("/batch", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun createPrescriptions(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestBody prescription: PrescriptionsRequest,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ): List<Prescription> =
        recipeV4Service.createPrescriptions(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            patient = prescription.patient!!,
            hcp = prescription.hcp!!,
            feedback = prescription.feedback!!,
            medications = prescription.medicationsBatches!!,
            prescriptionType = prescription.prescriptionType,
            notification = prescription.notification,
            executorId = prescription.executorId,
            samVersion = prescription.samVersion,
            deliveryDate = prescription.deliveryDate?.let {FuzzyValues.getLocalDateTime(it)},
            vendorName = prescription.vendorName ?: vendorName,
            packageName = prescription.packageName ?: packageVersion,
            packageVersion = prescription.packageVersion,
            vendorEmail = prescription.vendorEmail,
            vendorPhone = prescription.vendorPhone,
            vision = prescription.vision,
            visionOthers = prescription.visionOthers?. let { VisionOtherPrescribers.fromValue(it) },
            expirationDate = prescription.expirationDate?.let {FuzzyValues.getLocalDateTime(it)},
            lang = prescription.lang
        )


    @Operation(
        summary = "List open prescriptions for a patient",
        description = "Retrieves all open (non-expired, non-revoked) electronic prescriptions for a given patient from the Belgian Recipe system. The patient is identified by their SSIN."
    )
    @GetMapping("/patient", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun listOpenPrescriptionsByPatient(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam patientId: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ): List<Prescription> =
        recipeV4Service.listOpenPrescriptions(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            patientId = patientId,
            vendorName = vendorName,
            packageVersion = packageVersion
        )

    @Operation(
        summary = "List all prescriptions for a patient with filters",
        description = "Retrieves all electronic prescriptions for a given patient from the Belgian Recipe system, with optional filters for prescriber, date range, statuses, expiration range, and pagination. Returns a structured result with pagination metadata."
    )
    @GetMapping("/patient/all", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun listPrescriptionsByPatient(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam patientId: String,
        @RequestParam(required = false) prescriberId: String?,
        @RequestParam(required = false) from: Long?,
        @RequestParam(required = false) toInclusive: Long?,
        @RequestParam(required = false) statuses: String?,
        @RequestParam(required = false) expiringFrom: Long?,
        @RequestParam(required = false) expiringToInclusive: Long?,
        @RequestParam(required = false) pageYear: Int?,
        @RequestParam(required = false) pageMonth: Int?,
        @RequestParam(required = false) pageNumber: Long?,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ): ListStructuredPrescriptionsResult =
        recipeV4Service.listPrescriptions(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            patientId = patientId,
            prescriberId = prescriberId,
            from = from,
            toInclusive = toInclusive,
            statuses = statuses?.let { it.split(",").map { PrescriptionStatus.fromValue(it) } },
            expiringFrom = expiringFrom,
            expiringToInclusive = expiringToInclusive,
            pageYear = pageYear,
            pageMonth = pageMonth,
            pageNumber = pageNumber,
            vendorName = vendorName,
            packageVersion = packageVersion
        )

    @Operation(
        summary = "Send a notification for a prescription",
        description = "Sends a notification message related to an electronic prescription identified by its Recipe ID (RID). The notification is sent to the specified executor (e.g., pharmacist) to inform them about the prescription."
    )
    @PostMapping("/notify/{rid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendNotification(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @RequestParam patientId: String,
        @RequestParam executorId: String,
        @PathVariable rid: String,
        @RequestParam text: String,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ) = recipeV4Service.sendNotification(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        patientId = patientId,
        executorId = executorId,
        rid = rid,
        text = text,
        vendorName = vendorName,
        packageVersion = packageVersion
    )

    @Operation(
        summary = "Revoke an electronic prescription",
        description = "Revokes an existing electronic prescription identified by its Recipe ID (RID) in the Belgian Recipe system. A reason for the revocation must be provided. Once revoked, the prescription can no longer be dispensed."
    )
    @DeleteMapping("/{rid}")
    fun revokePrescription(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @PathVariable rid: String,
        @RequestParam reason: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ) =
        recipeV4Service.revokePrescription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            rid = rid,
            reason = reason,
            vendorName = vendorName,
            packageVersion = packageVersion
        )

    @Operation(
        summary = "Get the status of a prescription",
        description = "Retrieves the current status of an electronic prescription identified by its Recipe ID (RID) from the Belgian Recipe system. The status indicates whether the prescription is open, dispensed, revoked, or expired."
    )
    @GetMapping("/{rid}/status", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPrescriptionStatus(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @PathVariable rid: String,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ) =
        recipeV4Service.getPrescriptionStatus(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            rid = rid,
            vendorName = vendorName,
            packageVersion = packageVersion
        )

    @Operation(
        summary = "Update the feedback flag on a prescription",
        description = "Updates the feedback flag on an electronic prescription identified by its Recipe ID (RID). When feedback is allowed, the dispensing pharmacist can send feedback messages to the prescriber about the prescription."
    )
    @PutMapping("/{rid}/feedback/{feedbackFlag}")
    fun updateFeedbackFlag(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable rid: String,
        @PathVariable feedbackFlag: Boolean,
        @RequestParam hcpNihii: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ) =
        recipeV4Service.updateFeedbackFlag(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            rid = rid,
            feedbackAllowed = feedbackFlag,
            vendorName = vendorName,
            packageVersion = packageVersion
        )

    @Operation(
        summary = "Set the vision policy on a prescription",
        description = "Sets the vision (visibility) policy on an electronic prescription identified by its Recipe ID (RID). The vision controls which healthcare providers can view the prescription (e.g., open, locked, or restricted to GMD prescriber)."
    )
    @PutMapping("/{rid}/vision")
    fun setVision(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable rid: String,
        @RequestParam vision: String,
        @RequestParam(required = false) visionOthers: String?, //open, locked, gmd_prescriber
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
    @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ): PutVisionResult = recipeV4Service.setVision(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        rid = rid,
        vision = vision,
        visionOthers = visionOthers?.let { VisionOtherPrescribers.fromValue(it) },
        vendorName = vendorName,
        packageVersion = packageVersion
    )

    @Operation(
        summary = "Get the KMEHR prescription message",
        description = "Retrieves the full KMEHR XML message content of an electronic prescription identified by its Recipe ID (RID). The KMEHR message contains the structured clinical content of the prescription including medications, dosage, and patient information."
    )
    @GetMapping("/prescription/{rid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPrescriptionMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable rid: String,
        @RequestParam hcpNihii: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ): RecipeKmehrmessageType? =
        recipeV4Service.getPrescriptionMessage(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            rid = rid,
            vendorName = vendorName,
            packageVersion = packageVersion
        )

    @Operation(
        summary = "List all prescription feedbacks",
        description = "Retrieves all feedback messages sent by dispensing pharmacists for the authenticated prescriber's prescriptions. Feedbacks provide information about dispensing events, substitutions, or other notes from the pharmacist."
    )
    @GetMapping("/all/feedbacks", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun listFeedbacks(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) hcpNihii: String?,
        @RequestParam(required = false) hcpSsin: String?,
        @RequestParam(required = false) hcpName: String?,
        @RequestHeader(required = false, name = "X-FHC-vendorName") vendorName: String?,
        @RequestHeader(required = false, name = "X-FHC-packageVersion") packageVersion: String?
    ): List<Feedback> =
        recipeV4Service.listFeedbacks(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            vendorName = vendorName,
            packageVersion = packageVersion
        )

    @Operation(
        summary = "Get administration unit for a GAL code",
        description = "Maps a GAL (galenic form) identifier to its corresponding administration unit code. This is used to determine the correct administration unit for a medication based on its galenic form in the Belgian SAM (authentic source of medicines) database."
    )
    @GetMapping("/gal/{galId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getGalToAdministrationUnit(@PathVariable galId: String): Code? = recipeV4Service.getGalToAdministrationUnit(galId)

    @Operation(
        summary = "Get a prescription with feedback",
        description = "Retrieves a full electronic prescription along with its associated feedback messages, identified by its Recipe ID (RID). Returns the complete prescription details including medications, patient info, and any pharmacist feedback."
    )
    @GetMapping("/{rid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPrescription(@PathVariable rid: String): PrescriptionFullWithFeedback? = recipeV4Service.getPrescription(rid)
}
