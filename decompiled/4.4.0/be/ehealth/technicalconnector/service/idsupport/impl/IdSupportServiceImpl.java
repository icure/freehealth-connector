package be.ehealth.technicalconnector.service.idsupport.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.SchemaValidatorHandler;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.idsupport.IdSupportService;
import be.ehealth.technicalconnector.service.ws.ServiceFactory;
import be.ehealth.technicalconnector.session.AbstractSessionServiceWithCache;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.idsupport.core.v2.IdentificationData;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdRequest;
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdResponse;
import java.util.Arrays;
import javax.xml.soap.SOAPException;
import org.joda.time.DateTime;

public class IdSupportServiceImpl extends AbstractSessionServiceWithCache implements IdSupportService {
   static final String IDSUPPORT_XSD = "/ehealth-idsupport/XSD/ehealth-idsupport-protocol-2_0.xsd";
   private EhealthReplyValidator validator;

   public IdSupportServiceImpl(EhealthReplyValidator validator) {
      this.validator = validator;
   }

   public VerifyIdResponse verifyId(VerifyIdRequest request) throws TechnicalConnectorException {
      return this.verifyIdAndGenerateToken(request);
   }

   public VerifyIdResponse verifyId(String legalContext, Id ssin, Id cardNumber) throws TechnicalConnectorException {
      return this.verifyId(legalContext, identificationData(ssin, cardNumber));
   }

   public VerifyIdResponse verifyId(String legalContext, Id barcode) throws TechnicalConnectorException {
      return this.verifyId(legalContext, identificationData(barcode));
   }

   private VerifyIdResponse verifyId(String legalContext, IdentificationData identificationData) throws TechnicalConnectorException {
      VerifyIdRequest request = getBasicRequest(legalContext, "ID_" + IdGeneratorFactory.getIdGenerator("uuid").generateId());
      request.setIdentificationData(identificationData);
      return this.verifyId(request);
   }

   private VerifyIdResponse verifyIdAndGenerateToken(VerifyIdRequest verifyIdRequest) throws TechnicalConnectorException {
      try {
         GenericRequest genericRequest = ServiceFactory.getIdSupportV2Service(this.getSamlToken());
         genericRequest.setSoapAction("urn:be:fgov:ehealth:idsupport:protocol:v2:verifyId");
         HandlerChain handlers = new HandlerChain();
         handlers.register(HandlerPosition.BEFORE, new SchemaValidatorHandler(3, new String[]{"/ehealth-idsupport/XSD/ehealth-idsupport-protocol-2_0.xsd"}));
         genericRequest.addHandlerChain(handlers);
         genericRequest.setPayload((Object)verifyIdRequest);
         VerifyIdResponse response = (VerifyIdResponse)be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(genericRequest).asObject(VerifyIdResponse.class);
         this.validator.validateReplyStatus((StatusResponseType)response);
         return response;
      } catch (SOAPException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{e.getMessage(), e});
      }
   }

   private static VerifyIdRequest getBasicRequest(String legalContext, String id) {
      VerifyIdRequest request = new VerifyIdRequest();
      request.setId(id);
      request.setLegalContext(legalContext);
      request.setIssueInstant(new DateTime());
      return request;
   }

   private static IdentificationData identificationData(Id... ids) {
      IdentificationData identificationData = new IdentificationData();
      identificationData.getIds().addAll(Arrays.asList(ids));
      return identificationData;
   }
}
