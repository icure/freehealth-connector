package org.taktik.connector.business.mycarenetdomaincommons.util;

import org.taktik.connector.business.mycarenetdomaincommons.domain.McnPackageInfo;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;;

import java.io.File;
import java.io.IOException;

public final class McnConfigUtil {
   private static final String PACKAGE_LICENSE_USERNAME = "package.license.username";
   private static final String PACKAGE_LICENSE_PASSWORD = "package.license.password";
   private static final String PACKAGE_LICENSE_NAME = "package.name";

   private static final ConfigValidator configValidator = ConfigFactory.getConfigValidator();

   private static JsonNode config;

   static {
      try {
         // Load configuration file
         ObjectMapper mapper = new ObjectMapper();
         config = mapper.readTree(new File("/opt/freehealth/config.json"));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private McnConfigUtil() {
      throw new UnsupportedOperationException();
   }

   private static McnPackageInfo getMyCareNetConfig(String product, String quality) {
      String userName = "";
      String password = "";
      String packageName = "";

      if(config != null){
         JsonNode packageNode = config.path("packages").path(product);
         if (!packageNode.isMissingNode()) {
            JsonNode qualityNode = packageNode.path(quality);
            if (!qualityNode.isMissingNode()) {
               JsonNode usernameNode = qualityNode.path("mcnLicense");
               if (!usernameNode.isMissingNode()) {
                  userName = usernameNode.textValue();
               }

               JsonNode passwordNode = qualityNode.path("mcnPassword");
               if (!passwordNode.isMissingNode()) {
                  password = passwordNode.textValue();
               }

               JsonNode packageNameNode = qualityNode.path("mcnPackage");
               if (!packageNameNode.isMissingNode()) {
                  packageName = packageNameNode.textValue();
               }
            }
         }
      }

      return new McnPackageInfo(userName.trim(), password.trim(), packageName);
   }

   public static McnPackageInfo retrievePackageInfo(String componentName, String licenseUsername, String licensePassword, String packageName) {
      return retrievePackageInfo(componentName, licenseUsername, licensePassword, packageName, "", "");
   }

   public static McnPackageInfo retrievePackageInfo(String componentName, String licenseUsername, String licensePassword, String packageName, String product, String quality) {
      McnPackageInfo defaultPackageInfo = getMyCareNetConfig(product, quality);
      String userName = licenseUsername != null ? licenseUsername : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_USERNAME, defaultPackageInfo.getUserName() );
      String password = licensePassword != null ? licensePassword : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_PASSWORD, defaultPackageInfo.getPassword());
      String name = packageName != null ? packageName : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_NAME, defaultPackageInfo.getPackageName());
      return new McnPackageInfo(userName.trim(), password.trim(), name);
   }
}
