package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import jakarta.xml.ws.soap.SOAPFaultException;

public class SOAPUtils {
   private static final MessageFactory MF;

   protected SOAPUtils() {
   }

   public static SOAPFaultException newSOAPFaultException(String reasonText, Throwable cause) {
      try {
         SOAPFactory factory = SOAPFactory.newInstance();
         SOAPFault soapFault = factory.createFault();
         soapFault.setFaultString(reasonText);
         SOAPFaultException except = new SOAPFaultException(soapFault);
         except.initCause(cause);
         return except;
      } catch (SOAPException ex) {
         throw new IllegalArgumentException(ex);
      }
   }

   public static SOAPMessage newSOAPMessage(String payload) throws TechnicalConnectorException {
      try {
         SOAPMessage response = MF.createMessage();
         SOAPPart soapPart = response.getSOAPPart();
         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
         SOAPBody soapBody = soapEnvelope.getBody();
         soapBody.addDocument(ConnectorXmlUtils.toDocument(payload));
         return response;
      } catch (Exception ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_TECHNICAL, ex);
      }
   }

   static {
      try {
         MF = MessageFactory.newInstance();
      } catch (Exception var1) {
         throw new IllegalArgumentException(var1);
      }
   }
}
