package org.taktik.connector.technical.handler.wss4j;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;

public class RSAAlgorithmHelper implements AlgorithmHelper {
   public static final String PROP_DEFAULT_DIGEST_ALGO = "default.rsa.digest.method.algorithm";
   public static final String PROP_DEFAULT_SIGNATURE_ALGO = "default.rsa.signature.method.algorithm";

   public RSAAlgorithmHelper() {
   }

   public boolean canHandle(Credential cred) {
      try {
         return "RSA".equals(cred.getPrivateKey().getAlgorithm());
      } catch (TechnicalConnectorException var3) {
         return false;
      }
   }

   public String determineDigestAlgo(Credential cred) {
      return ConfigFactory.getConfigValidator().getProperty("default.rsa.digest.method.algorithm", "http://www.w3.org/2001/04/xmlenc#sha256");
   }

   public String determineSignatureAlgorithm(Credential cred) {
      return ConfigFactory.getConfigValidator().getProperty("default.rsa.signature.method.algorithm", "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
   }
}
