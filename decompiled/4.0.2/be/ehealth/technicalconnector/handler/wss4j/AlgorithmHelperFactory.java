package be.ehealth.technicalconnector.handler.wss4j;

import be.ehealth.technicalconnector.service.sts.security.Credential;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmHelperFactory {
   private static List<AlgorithmHelper> helpers = new ArrayList();

   public AlgorithmHelperFactory() {
   }

   public static AlgorithmHelper getAlgorithmHelper(Credential credential) {
      for(AlgorithmHelper helper : helpers) {
         if (helper.canHandle(credential)) {
            return helper;
         }
      }

      throw new IllegalArgumentException("Unsupported algorithm " + credential);
   }

   static {
      helpers.add(new RSAAlgorithmHelper());
      helpers.add(new ECAlgorithmHelper());
   }
}
