package org.taktik.connector.technical.handler.wss4j;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public class SOAPContextAlgorithmHelper implements AlgorithmHelper {
   private final SOAPMessageContext ctx;

   public boolean canHandle(Credential cred) {
      return this.ctx.containsKey("org.taktik.connector.technical.ws.feature.signing");
   }

   public String determineDigestAlgo(Credential cred) throws TechnicalConnectorException {
      switch (cred.getPrivateKey().getAlgorithm()) {
         case "EC":
            return (String)this.ctx.get("ec.digest.method.algorithm");
         case "RSA":
            return (String)this.ctx.get("rsa.digest.method.algorithm");
         default:
            throw new IllegalArgumentException("Unsupported algorithm " + cred);
      }
   }

   public String determineSignatureAlgorithm(Credential cred) throws TechnicalConnectorException {
      switch (cred.getPrivateKey().getAlgorithm()) {
         case "EC":
            return (String)this.ctx.get("ec.signature.method.algorithm");
         case "RSA":
            return (String)this.ctx.get("rsa.signature.method.algorithm");
         default:
            throw new IllegalArgumentException("Unsupported algorithm " + cred);
      }
   }

   public SOAPContextAlgorithmHelper(SOAPMessageContext ctx) {
      this.ctx = ctx;
   }
}
