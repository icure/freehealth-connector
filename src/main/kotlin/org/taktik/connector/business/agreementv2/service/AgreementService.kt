package org.taktik.connector.business.agreementv2.service

import be.fgov.ehealth.mycarenet.agreement.protocol.v2.AskAgreementRequest
import be.fgov.ehealth.mycarenet.agreement.protocol.v2.AskAgreementResponse
import be.fgov.ehealth.mycarenet.agreement.protocol.v2.ConsultAgreementRequest
import be.fgov.ehealth.mycarenet.agreement.protocol.v2.ConsultAgreementResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface AgreementService {
    @Throws(TechnicalConnectorException::class)
    fun askAgreement(samlToken: SAMLToken, askAgreementRequest: AskAgreementRequest?): AskAgreementResponse?

    @Throws(TechnicalConnectorException::class)
    fun consultAgreement(samlToken: SAMLToken, consultAgreementRequest: ConsultAgreementRequest?): ConsultAgreementResponse?
}
