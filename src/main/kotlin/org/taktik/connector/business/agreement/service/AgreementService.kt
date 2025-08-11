package org.taktik.connector.business.agreement.service

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface AgreementService {
    @Throws(TechnicalConnectorException::class)
    fun askAgreement(samlToken: SAMLToken, askAgreementRequest: AskAgreementRequest?): AskAgreementResponse?

    @Throws(TechnicalConnectorException::class)
    fun consultAgreement(samlToken: SAMLToken, consultAgreementRequest: ConsultAgreementRequest?): ConsultAgreementResponse?
}
}
