package be.fgov.ehealth.technicalconnector.bootstrap.bcp.verifier;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StatusPageSignatureVerifier {
   private static final Logger LOG = LoggerFactory.getLogger(StatusPageSignatureVerifier.class);

   private StatusPageSignatureVerifier() {
      throw new UnsupportedOperationException();
   }

   public static boolean isValid(String xml) throws TechnicalConnectorException {
      try {
         Map<String, Object> options = new HashMap();
         SignatureVerificationResult signatureResult = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES).verify(xml.getBytes(), options);
         if (!signatureResult.isValid()) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SIGNATURE_VALIDATION, new Object[]{ArrayUtils.toString(signatureResult.getErrors().toArray())});
         } else {
            return true;
         }
      } catch (Exception e) {
         LOG.error("Unable to verify signature Reason:" + e.getMessage(), e);
         return false;
      }
   }
}
