package be.ehealth.technicalconnector.ws.feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractSigningFeature extends GenericFeature {
   public static final String ID = "be.ehealth.technicalconnector.ws.feature.signing";
   public static final String EC_SIGNATURE_METHOD_ALGORITHM = "ec.signature.method.algorithm";
   public static final String RSA_SIGNATURE_METHOD_ALGORITHM = "rsa.signature.method.algorithm";
   public static final String RSA_DIGEST_METHOD_ALGORITHM = "rsa.digest.method.algorithm";
   public static final String EC_DIGEST_METHOD_ALGORITHM = "ec.digest.method.algorithm";

   public AbstractSigningFeature() {
      super(true);
   }

   public String getID() {
      return "be.ehealth.technicalconnector.ws.feature.signing";
   }

   protected abstract Optional<String> getSignatureRSAMethodAlgorithm();

   protected abstract Optional<String> getSignatureECMethodAlgorithm();

   protected abstract Optional<String> getDigestRSAMethodAlgorithm();

   protected abstract Optional<String> getDigestECMethodAlgorithm();

   public Map<String, Object> requestParamOptions() {
      HashMap<String, Object> options = new HashMap();
      options.put("be.ehealth.technicalconnector.ws.feature.signing", "true");
      options.putAll(this.add("rsa.signature.method.algorithm", this.getSignatureRSAMethodAlgorithm()));
      options.putAll(this.add("rsa.digest.method.algorithm", this.getDigestRSAMethodAlgorithm()));
      options.putAll(this.add("ec.signature.method.algorithm", this.getSignatureECMethodAlgorithm()));
      options.putAll(this.add("ec.digest.method.algorithm", this.getDigestECMethodAlgorithm()));
      return options;
   }

   private Map<String, Object> add(String key, Optional<String> value) {
      HashMap<String, Object> option = new HashMap();
      if (value.isPresent()) {
         option.put(key, value.get());
      }

      return option;
   }
}
