package be.ehealth.businessconnector.mycarenet.attestv2.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.Crypto;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;

public class AttestEncryptionUtil<X> {
   public AttestEncryptionUtil() {
   }

   public byte[] handleEncryptionSendAttestation(X request, Crypto crypto, String detailId) throws TechnicalConnectorException {
      try {
         Marshaller marshaller = JAXBContext.newInstance(new Class[]{request.getClass()}).createMarshaller();
         DOMResult res = new DOMResult();
         marshaller.marshal(request, res);
         return SendBusinessContentEncryptor.encrypt((Document)res.getNode(), crypto, detailId);
      } catch (JAXBException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, e, new Object[0]);
      }
   }
}
