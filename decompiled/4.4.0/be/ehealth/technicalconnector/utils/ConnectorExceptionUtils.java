package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.SoaErrorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.fgov.ehealth.errors.core.v1.ErrorType;
import be.fgov.ehealth.errors.soa.v1.BusinessError;
import be.fgov.ehealth.errors.soa.v1.SystemError;
import be.fgov.ehealth.etee.crypto.decrypt.UnsealedData;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import javax.xml.ws.soap.SOAPFaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public final class ConnectorExceptionUtils {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorExceptionUtils.class);

   private ConnectorExceptionUtils() {
   }

   public static void processSOAPFault(SOAPFaultException e) throws SoaErrorException {
      if (e.getFault() == null) {
         throw e;
      } else {
         Node detail = ConnectorXmlUtils.getFirstChildElement(e.getFault().getDetail());
         if (detail == null) {
            throw e;
         } else if ("BusinessError".equals(detail.getLocalName())) {
            MarshallerHelper<BusinessError, BusinessError> helper = new MarshallerHelper<BusinessError, BusinessError>(BusinessError.class, BusinessError.class);
            throw new SoaErrorException(e.getFault().getFaultCode() + ": " + e.getFault().getFaultString(), (ErrorType)helper.toObject(detail));
         } else if ("SystemError".equals(detail.getLocalName())) {
            MarshallerHelper<SystemError, SystemError> helper = new MarshallerHelper<SystemError, SystemError>(SystemError.class, SystemError.class);
            throw new SoaErrorException(e.getFault().getFaultCode() + ": " + e.getFault().getFaultString(), (ErrorType)helper.toObject(detail));
         } else {
            throw e;
         }
      }
   }

   public static byte[] processUnsealConnectorException(UnsealConnectorException e, String... allowedErrors) throws TechnicalConnectorException {
      CryptoResult<UnsealedData> result = e.getUnsealResult();
      Set<NotificationError> errors = new HashSet();
      errors.addAll(result.getErrors());

      for(NotificationError error : errors) {
         LOG.error("NotificationError: {}", error);
      }

      if (allowedErrors != null && allowedErrors.length != 0) {
         for(String allowedError : allowedErrors) {
            NotificationError error = NotificationError.valueOf(allowedError);
            errors.remove(error);
         }
      } else {
         LOG.warn("Empty error list to ignore. Ignoring all errors.");
         errors.clear();
      }

      if (errors.isEmpty() && result.getFatal() == null) {
         InputStream unsealedDataStream = ((UnsealedData)result.getData()).getContent();
         return ConnectorIOUtils.getBytes(unsealedDataStream);
      } else {
         throw e;
      }
   }
}
