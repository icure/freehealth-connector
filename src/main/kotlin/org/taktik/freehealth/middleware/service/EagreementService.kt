package org.taktik.freehealth.middleware.service

import org.joda.time.DateTime
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl
import java.util.*

interface EagreementService {
    /**
     * @name askAgreement
     * @goal Request new agreement
     * @goal Argue a request being processed
     * @goal Extend an existing agreement
     * @goal Cancel request in the event of an error
     * @goal Complete existing ask agreement
     * */
    fun askAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        requestType: EagreementServiceImpl.RequestTypeEnum,
        hcpQuality: String,
        messageEventSystem: EagreementServiceImpl.MessageEventSystemEnum,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        pathologyStartDate: DateTime?,
        pathologyCode: String?,
        insuranceRef: String?,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?,
        sctCode: String?,
        sctDisplay: String?
    ): AgreementResponse?

    /**
     * @name consultAgreement
     * @goal Consult the request for agreement of a patient
     * */
    fun consultAgreementList(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        requestType: EagreementServiceImpl.RequestTypeEnum,
        hcpQuality: String,
        messageEventSystem: EagreementServiceImpl.MessageEventSystemEnum,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        subTypeCode: String,
        insuranceRef: String?,
        orgNihii: String?,
        organizationType: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?
    ): AgreementResponse?

}
