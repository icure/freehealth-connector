package org.taktik.connector.business.recipe.prescriber

import be.fgov.ehealth.recipe.protocol.v4.ListPrescriptionsResult
import be.recipe.services.core.PrescriptionStatus
import be.recipe.services.core.VisionOtherPrescribers
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult
import be.recipe.services.prescriber.GetPrescriptionStatusResult
import be.recipe.services.prescriber.ListOpenRidsResult
import be.recipe.services.prescriber.ListRidsHistoryResult
import be.recipe.services.prescriber.PutVisionResult
import be.recipe.services.prescriber.RevokePrescriptionResult
import be.recipe.services.prescriber.ValidationPropertiesParam
import be.recipe.services.prescriber.ValidationPropertiesResult
import org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import java.time.LocalDateTime


interface PrescriberIntegrationModuleV4 {
    fun getPrescription(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        vendorName: String? = null,
        packageVersion: String? = null
    ): GetPrescriptionForPrescriberResult?

    fun createPrescription(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        patientSsin: String,
        nihii: String,
        feedbackRequested: Boolean,
        prescriptionType: String,
        expirationDate: LocalDateTime = LocalDateTime.now().plusMonths(3),
        prescription: ByteArray,
        visibility: String? = null,
        visionOthers: VisionOtherPrescribers? = null,
        vendorName: String? = null,
        packageVersion: String? = null
    ): String?

    fun getPrescriptionStatus(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        vendorName: String? = null,
        packageVersion: String? = null
    ): GetPrescriptionStatusResult?

    fun listRidsHistory(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        patientSsin: String,
        vendorName: String? = null,
        packageVersion: String? = null
    ): ListRidsHistoryResult?

    fun setVision(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        rid: String,
        vision: String,
        visionOthers: VisionOtherPrescribers?,
        vendorName: String? = null,
        packageVersion: String? = null
    ): PutVisionResult?

    fun listOpenRids(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        patientSsin: String,
        vendorName: String? = null,
        packageVersion: String? = null
    ): ListOpenRidsResult?

    fun revokePrescription(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        reason: String,
        vendorName: String? = null,
        packageVersion: String? = null
    ): RevokePrescriptionResult

    fun listFeedback(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        readFlag: Boolean,
        vendorName: String? = null,
        packageVersion: String? = null
    ): List<ListFeedbackItem>

    fun getValidationProperties(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        param: ValidationPropertiesParam,
        vendorName: String? = null,
        packageVersion: String? = null
    ): ValidationPropertiesResult?

    fun listPrescriptions(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        patientSsin: String,
        prescriberId: String? = null,
        from: Long? = null,
        toInclusive: Long? = null,
        statuses: List<PrescriptionStatus>? = null,
        expiringFrom: Long? = null,
        expiringToInclusive: Long? = null,
        pageYear: Int? = null,
        pageMonth: Int? = null,
        pageNumber: Long? = null,
        vendorName: String? = null,
        packageVersion: String? = null
    ): org.taktik.freehealth.middleware.dto.recipe.ListPrescriptionsResult
}
