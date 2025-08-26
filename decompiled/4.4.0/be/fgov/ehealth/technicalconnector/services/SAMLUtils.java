package be.fgov.ehealth.technicalconnector.services;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class SAMLUtils {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLUtils.class);
   private static final String ASSERTION_NAMESPACE = "urn:oasis:names:tc:SAML:2.0:assertion";
   private static final String PROTOCOL_NAMESPACE = "urn:oasis:names:tc:SAML:2.0:protocol";

   public SAMLUtils() {
   }

   public static List<String> getStatusCodes(Element response) {
      List<String> result = new ArrayList();
      Element status = ConnectorXmlUtils.getFirstElementByTagNameNS(response, "urn:oasis:names:tc:SAML:2.0:protocol", "Status");
      NodeList statusCodes = status.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:protocol", "StatusCode");

      for(int i = 0; i < statusCodes.getLength(); ++i) {
         result.add(statusCodes.item(i).getAttributes().getNamedItem("Value").getTextContent());
      }

      return result;
   }

   public static String getStatusMessage(Element response) {
      Element status = ConnectorXmlUtils.getFirstElementByTagNameNS(response, "urn:oasis:names:tc:SAML:2.0:protocol", "Status");
      Element statusMessage = ConnectorXmlUtils.getFirstElementByTagNameNS(status, "urn:oasis:names:tc:SAML:2.0:protocol", "StatusMessage");
      if (statusMessage != null) {
         return statusMessage.getTextContent();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static NodeList getAssertions(Element response) {
      return response.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");
   }

   public static void validateAssertions(Element response) throws TechnicalConnectorException {
      NodeList assertions = getAssertions(response);

      for(int i = 0; i < assertions.getLength(); ++i) {
         validateAssertion((Element)assertions.item(i));
      }

   }

   public static void validateAssertion(Element assertion) throws TechnicalConnectorException {
      byte[] signed = ConnectorXmlUtils.toByteArray((Node)assertion);
      String id = assertion.getAttributes().getNamedItem("ID").getTextContent();
      Map<String, Object> options = new HashMap();
      options.put("baseURI", id);
      SignatureVerificationResult verificationResult = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XML).verify(signed, options);
      if (!verificationResult.isValid()) {
         if (!verificationResult.getErrors().contains(SignatureVerificationError.SIGNATURE_NOT_PRESENT)) {
            for(SignatureVerificationError signatureVerificationError : verificationResult.getErrors()) {
               LOG.error(signatureVerificationError.getMessage());
            }

            throw new IllegalArgumentException();
         }
      } else {
         LOG.debug("SAML assertion correctly signed!");
      }

   }
}
