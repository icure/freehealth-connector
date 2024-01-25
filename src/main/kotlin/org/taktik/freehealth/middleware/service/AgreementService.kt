package org.taktik.freehealth.middleware.service

import org.joda.time.DateTime
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.freehealth.middleware.service.impl.AgreementServiceImpl
import java.util.*

interface AgreementService {
    fun consultSynchronousAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        requestType: AgreementServiceImpl.RequestTypeEnum,
        messageEventSystem: String,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        pathologyStartDate: DateTime,
        pathologyCode: String,
        insuranceRef: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        parameterNames: Array<String>?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?
    ): AgreementResponse?

    fun requestAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String,
        patientSsin: String
    ): AgreementResponse?

    // abstract fun LicenseType(): Any

}
