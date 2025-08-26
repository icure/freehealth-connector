package be.ehealth.business.common.helper;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.commons.core.v2.StatusDetail;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.soap.SOAPException;
import org.apache.commons.collections.CollectionUtils;
import org.w3c.dom.Node;

public final class EhealthServiceHelper {
   private EhealthServiceHelper() {
   }

   public static <T extends ResponseType> T callEhealthServiceV1(SAMLToken token, GenericRequest service, Object request, Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator ehealthReplyValidator) throws TechnicalConnectorException {
      try {
         sessionValidator.validateToken(token);
         service.setPayload(request);
         T response = (T)(ServiceFactory.getGenericWsSender().send(service).asObject(clazz));
         ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, new Object[]{e.getMessage()});
      }
   }

   public static <T extends be.fgov.ehealth.commons._1_0.protocol.ResponseType> T callEhealthService_1_0(SAMLToken token, GenericRequest service, Object request, Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator ehealthReplyValidator) throws TechnicalConnectorException {
      try {
         sessionValidator.validateToken(token);
         service.setPayload(request);
         T response = (T)(ServiceFactory.getGenericWsSender().send(service).asObject(clazz));
         ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, new Object[]{e.getMessage()});
      }
   }

   public static <T extends StatusResponseType> T callEhealthServiceV2(SAMLToken token, GenericRequest service, Object request, Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator ehealthReplyValidator) throws TechnicalConnectorException {
      try {
         sessionValidator.validateToken(token);
         service.setPayload(request);
         T response = (T)(ServiceFactory.getGenericWsSender().send(service).asObject(clazz));
         ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, new Object[]{e.getMessage()});
      }
   }

   public static <T> List<T> toList(StatusDetail statusDetail, Class<T> clazz) {
      List<T> list = new ArrayList();
      if (statusDetail != null && CollectionUtils.isNotEmpty(statusDetail.getAnies())) {
         for(Object object : statusDetail.getAnies()) {
            list.add(ConnectorXmlUtils.toObject(ConnectorXmlUtils.toByteArray((Node)object), clazz));
         }
      }

      return list;
   }

   public static <T> T getFirst(StatusDetail statusDetail, Class<T> clazz) {
      List<T> list = toList(statusDetail, clazz);
      return (T)(CollectionUtils.isNotEmpty(list) ? list.get(0) : null);
   }
}
