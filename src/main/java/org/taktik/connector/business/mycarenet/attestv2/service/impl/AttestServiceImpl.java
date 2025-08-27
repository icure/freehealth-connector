package org.taktik.connector.business.mycarenet.attestv2.service.impl;

import org.taktik.connector.business.mycarenet.attestv2.service.AttestService;
import org.taktik.connector.business.mycarenet.attestv2.service.ServiceFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import jakarta.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttestServiceImpl implements AttestService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(AttestServiceImpl.class);

   public AttestServiceImpl(EhealthReplyValidator replyValidator) {

   }

   public AttestServiceImpl() {
      LOG.debug("creating AttestServiceImpl for bootstrapping purposes");
   }

   public final SendAttestationResponse sendAttestion(SAMLToken token, SendRequestType request) throws TechnicalConnectorException {
      try {
         GenericRequest service = ServiceFactory.getAttestPort(token);
         service.setPayload((Object)request);
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         return (SendAttestationResponse)xmlResponse.asObject(SendAttestationResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public CancelAttestationResponse cancelAttestion(SAMLToken token, SendRequestType request) throws TechnicalConnectorException {
      try {
         GenericRequest service = ServiceFactory.getAttestPort(token);
         service.setPayload((Object)request);
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
         return (CancelAttestationResponse)xmlResponse.asObject(CancelAttestationResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SendAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(CancelAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(CancelAttestationResponse.class);
   }
}
