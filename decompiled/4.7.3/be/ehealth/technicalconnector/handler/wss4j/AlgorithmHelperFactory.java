package be.ehealth.technicalconnector.handler.wss4j;

import be.ehealth.technicalconnector.service.sts.security.Credential;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class AlgorithmHelperFactory {
   private static List<AlgorithmHelper> defaultHelpers = new ArrayList();

   public AlgorithmHelperFactory() {
   }

   public static AlgorithmHelper getAlgorithmHelper(Credential credential, SOAPMessageContext ctx) {
      List<AlgorithmHelper> helpers = new ArrayList();
      if (ctx != null) {
         helpers.add(new SOAPContextAlgorithmHelper(ctx));
      }

      helpers.addAll(defaultHelpers);

      for(AlgorithmHelper helper : helpers) {
         if (helper.canHandle(credential)) {
            return helper;
         }
      }

      throw new IllegalArgumentException("Unsupported algorithm " + credential);
   }

   static {
      defaultHelpers.add(new RSAAlgorithmHelper());
      defaultHelpers.add(new ECAlgorithmHelper());
   }
}
