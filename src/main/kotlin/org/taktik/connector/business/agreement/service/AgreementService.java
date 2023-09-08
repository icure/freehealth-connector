package org.taktik.connector.business.agreement.service;

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface AgreementService {
   AskAgreementResponse askAgreement(SAMLToken samlToken, AskAgreementRequest askAgreementRequest) throws TechnicalConnectorException;

   ConsultAgreementResponse consultAgreement(SAMLToken samlToken, ConsultAgreementRequest consultAgreementRequest) throws TechnicalConnectorException;
}
