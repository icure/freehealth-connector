package be.ehealth.technicalconnector.ws.feature;

import java.util.Optional;

public class SHA256Feature extends AbstractSigningFeature {
   public SHA256Feature() {
   }

   protected Optional<String> getSignatureRSAMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
   }

   protected Optional<String> getSignatureECMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha256");
   }

   protected Optional<String> getDigestRSAMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2001/04/xmlenc#sha256");
   }

   protected Optional<String> getDigestECMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2001/04/xmlenc#sha256");
   }
}
