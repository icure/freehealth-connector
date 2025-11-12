package be.ehealth.technicalconnector.ws.feature;

import java.util.Optional;

public class SHA1Feature extends AbstractSigningFeature {
   public SHA1Feature() {
   }

   protected Optional<String> getSignatureRSAMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2000/09/xmldsig#rsa-sha1");
   }

   protected Optional<String> getSignatureECMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha1");
   }

   protected Optional<String> getDigestRSAMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2000/09/xmldsig#sha1");
   }

   protected Optional<String> getDigestECMethodAlgorithm() {
      return Optional.of("http://www.w3.org/2000/09/xmldsig#sha1");
   }
}
