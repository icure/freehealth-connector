package org.taktik.connector.business.agreement.service.impl;

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.business.agreement.service.AgreementService;
import org.taktik.connector.business.agreement.service.ServiceFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;

public class AgreementServiceImpl implements AgreementService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(AgreementServiceImpl.class);
   public static final String SOAP_ACTION_ASK_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:AskAgreement";
   public static final String SOAP_ACTION_CONSULT_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:ConsultAgreement";

   public AgreementServiceImpl() {
      LOG.debug("creating AgreementServiceImpl for bootstrapping purposes");
   }

   public AskAgreementResponse askAgreement(SAMLToken samlToken, AskAgreementRequest askAgreementRequest) throws TechnicalConnectorException {
      return this.callAgreementService(samlToken, askAgreementRequest, "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:AskAgreement", AskAgreementResponse.class);
   }

   public ConsultAgreementResponse consultAgreement(SAMLToken samlToken, ConsultAgreementRequest consultAgreementRequest) throws TechnicalConnectorException {
      return this.callAgreementService(samlToken, consultAgreementRequest, "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:ConsultAgreement", ConsultAgreementResponse.class);
   }

   private <T extends SendRequestType, K extends SendResponseType> K callAgreementService(SAMLToken token, T request, String soapAction, Class<K> responseClass) throws TechnicalConnectorException {
      try {
         GenericRequest service = ServiceFactory.getAgreementPort(token);
         service.setSoapAction(soapAction);
         service.setPayload(request);
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         return xmlResponse.asObject(responseClass);
      } catch (SOAPException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, e.getMessage());
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(AskAgreementRequest.class);
      JaxbContextFactory.initJaxbContext(AskAgreementResponse.class);
      JaxbContextFactory.initJaxbContext(ConsultAgreementRequest.class);
      JaxbContextFactory.initJaxbContext(ConsultAgreementResponse.class);
   }
}
