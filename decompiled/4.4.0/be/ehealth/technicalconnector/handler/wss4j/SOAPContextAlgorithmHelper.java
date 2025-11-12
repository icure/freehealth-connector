package be.ehealth.technicalconnector.handler.wss4j;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SOAPContextAlgorithmHelper implements AlgorithmHelper {
   private final SOAPMessageContext ctx;

   public boolean canHandle(Credential cred) {
      return this.ctx.containsKey("be.ehealth.technicalconnector.ws.feature.signing");
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
