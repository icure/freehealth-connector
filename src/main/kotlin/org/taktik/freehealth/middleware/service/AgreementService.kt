package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse
import org.joda.time.DateTime
import org.taktik.connector.business.domain.agreement.AgreementResponse
import java.util.UUID

interface AgreementService {
    fun consultAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        requestType: String,
        hcpSsin: String,
        hcpNihii: String,
        hcpFirstName: String,
        hcpLastName: String,
        hcpSpeciality: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        io: String?,
        ioMembership: String?,
        code: String?,
        insuranceRef: String?,
        startDate: DateTime?,
        quantity: Int?,
        pathologyDate: DateTime?,
        pathologyCode: String
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

}
