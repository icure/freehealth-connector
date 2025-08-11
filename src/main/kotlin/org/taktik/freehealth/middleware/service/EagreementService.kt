package org.taktik.freehealth.middleware.service

import org.joda.time.DateTime
import org.taktik.connector.business.agreement.domain.Agreement
import org.taktik.connector.business.agreement.domain.AgreementMessage
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.connector.business.domain.agreement.EAgreementResponse
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl
import org.taktik.freehealth.middleware.web.controllers.EagreementController
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
        agreement: Agreement
    ): AgreementResponse?

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
        prescriberNihii: String?,
        prescriberFirstName: String?,
        prescriberLastName: String?,
        orgNihii: String?,
        organizationType: String?,
        prescription1: String?,
        prescription2: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForPrescription1: Float?,
        numberOfSessionForPrescription2: Float?,
        sctCode: String?,
        sctDisplay: String?,
        attachments: List<EagreementController.Attachment>?
    ): EAgreementResponse?

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
        insuranceRef: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        subTypeCode: String,
        orgNihii: String?,
        organizationType: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?
    ): AgreementResponse?


    /**
     * @name consultAgreement
     * @goal Consult the request for agreement of a patient
     * */
  fun consultAgreementList(
      keystoreId: UUID,
      tokenId: UUID,
      passPhrase: String,
      agreement: Agreement
  ): AgreementResponse?

  fun getEAgreementMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        limit: Int
    ): List<AgreementMessage>

  fun confirmMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        references: List<String>
    ): Boolean
}
