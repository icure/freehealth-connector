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
import org.taktik.freehealth.middleware.service.RecipeV4Service
import org.taktik.freehealth.utils.FuzzyValues
import java.util.*

@RestController
@RequestMapping("/recipe")
class RecipeController(val recipeV4Service: RecipeV4Service) {
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

    @GetMapping("/gal/{galId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getGalToAdministrationUnit(@PathVariable galId: String): Code? = recipeV4Service.getGalToAdministrationUnit(galId)

    @GetMapping("/{rid}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPrescription(@PathVariable rid: String): PrescriptionFullWithFeedback? = recipeV4Service.getPrescription(rid)
}
