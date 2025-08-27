package be.ehealth.business.intrahubcommons.helper;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;

public class RequestBuilderHelper {
   private static final Configuration config = ConfigFactory.getConfigValidator();

   private RequestBuilderHelper() {
      throw new IllegalStateException("Utility class");
   }

   public static IDKMEHR createKmehrId(String projectName, String isCbePropertyKey) throws TechnicalConnectorException {
      String kmehrIdSuffix = null;
      if (!isCbeSession(isCbePropertyKey)) {
         kmehrIdSuffix = SessionUtil.getNihii();
      }

      return HcPartyUtil.createKmehrId(projectName, kmehrIdSuffix);
   }

   private static boolean isCbeSession(String isCbePropertyKey) {
      return config.getBooleanProperty(isCbePropertyKey, false);
   }
}
