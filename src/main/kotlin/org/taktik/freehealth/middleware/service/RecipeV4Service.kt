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

package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.recipe.protocol.v4.ListPrescriptionsResult
import be.recipe.services.core.PrescriptionStatus
import be.recipe.services.core.VisionOtherPrescribers
import be.recipe.services.prescriber.GetPrescriptionStatusResult
import be.recipe.services.prescriber.ListRidsHistoryResult
import be.recipe.services.prescriber.PutVisionResult
import be.recipe.services.prescriber.UpdateFeedbackFlagResult
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeKmehrmessageType
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.recipe.Feedback
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.domain.recipe.PrescriptionFullWithFeedback
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.dto.recipe.ListStructuredPrescriptionsResult
import org.taktik.icure.be.ehealth.logic.recipe.impl.KmehrPrescriptionConfig
import java.time.LocalDateTime
import java.util.UUID

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 16/06/13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
interface RecipeV4Service {
    fun createPrescription(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpQuality: String,
        hcpNihii: String,
        patient: Patient,
        hcp: HealthcareParty,
        feedback: Boolean,
        medications: List<Medication>,
        prescriptionType: String?,
        notification: String?,
        executorId: String?,
        samVersion: String?,
        deliveryDate: LocalDateTime?,
        vendorName: String? = null,
        packageName: String? = null,
        packageVersion: String? = null,
        vendorEmail: String? = null,
        vendorPhone: String? = null,
        vision: String? = null,
        visionOthers: VisionOtherPrescribers? = null,
        expirationDate: LocalDateTime? = null,
        lang: String?
    ): Prescription

    fun listOpenPrescriptions(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        patientId: String,
        vendorName: String?,
        packageVersion: String?
    ): List<Prescription>

    fun listFeedbacks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        vendorName: String?,
        packageVersion: String?
    ): List<Feedback>

    fun revokePrescription(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        rid: String,
        reason: String,
        vendorName: String?,
        packageVersion: String?
    )

    fun getPrescriptionStatus(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        rid: String,
        vendorName: String?,
        packageVersion: String?
    ): GetPrescriptionStatusResult

    fun sendNotification(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        patientId: String,
        executorId: String,
        rid: String,
        text: String,
        vendorName: String?,
        packageVersion: String?
    )

    fun setVision(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        rid: String,
        vision: String,
        visionOthers: VisionOtherPrescribers?,
        vendorName: String?,
        packageVersion: String?
    ): PutVisionResult

    fun listRidsHistory(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        patientSsin: String,
        rid: String,
        reason: String,
        vendorName: String?,
        packageVersion: String?
    ): ListRidsHistoryResult

    fun updateFeedbackFlag(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        rid: String,
        feedbackAllowed: Boolean,
        vendorName: String?,
        packageVersion: String?
    ): UpdateFeedbackFlagResult

    fun getGalToAdministrationUnit(galId: String): Code?
    fun getPrescription(rid: String): PrescriptionFullWithFeedback?
    fun getPrescriptionMessage(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        rid: String,
        vendorName: String?,
        packageVersion: String?
    ): RecipeKmehrmessageType?

    fun inferPrescriptionType(medications: List<Medication>, prescriptionType: String?): String
    fun getKmehrPrescription(
        patient: Patient,
        hcp: HealthcareParty,
        medications: List<Medication>,
        deliveryDate: LocalDateTime?,
        config: KmehrPrescriptionConfig,
        hcpQuality: String,
        expirationDate: LocalDateTime?
    ): org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage

    fun listPrescriptions(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        patientId: String,
        prescriberId: String?,
        from: Long?,
        toInclusive: Long?,
        statuses: List<PrescriptionStatus>?,
        expiringFrom: Long?,
        expiringToInclusive: Long?,
        pageYear: Int?,
        pageMonth: Int?,
        pageNumber: Long?,
        vendorName: String?,
        packageVersion: String?
    ): ListStructuredPrescriptionsResult
}
