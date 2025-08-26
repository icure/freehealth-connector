package be.fgov.ehealth.technicalconnector.bootstrap.bcp;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.parser.StatusPageParser;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.verifier.StatusPageSignatureVerifier;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EndpointUpdater {
   public static final String PROP_BCP_STATUS_ENDPOINT = "be.ehealth.technicalconnector.bcp.status.endpoint";
   public static final String PROP_BCP_CACHE_DIR = "bcp.local.cache.dir";
   private static final Logger LOG = LoggerFactory.getLogger(EndpointUpdater.class);
   private static String loadedSha2;
   private static String loadedSha2Location;
   private static String loadedXmlLocation;

   private EndpointUpdater() {
      throw new UnsupportedOperationException();
   }

   public static boolean update() throws TechnicalConnectorException {
      String endpoint = determineEndpoint();
      String onlineSha2 = ConnectorIOUtils.getResourceAsString(endpoint + ".sha2");
      if (!onlineSha2.equals(loadedSha2)) {
         String content = ConnectorIOUtils.getResourceAsString(endpoint + ".xml");
         update(content);
         write(content, loadedXmlLocation);
         write(onlineSha2, loadedSha2Location);
         loadedSha2 = onlineSha2;
         return true;
      } else {
         LOG.debug("No change detected");
         return false;
      }
   }

   private static void write(String content, String location) {
      Validate.notEmpty(location);
      OutputStream fos = null;

      try {
         File output = new File(location);
         if (!output.exists() && !output.createNewFile()) {
            throw new IOException("Unable to create new file. [" + location + "]");
         }

         fos = new FileOutputStream(output);
         IOUtils.write(content.getBytes(Charset.UTF_8.getName()), fos);
      } catch (IOException e) {
         LOG.error("Unable to write content to file [" + location + "]", e);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }

   }

   private static boolean update(String xml) throws TechnicalConnectorException {
      if (StatusPageSignatureVerifier.isValid(xml)) {
         EndpointDistributor.getInstance().update(StatusPageParser.parse(xml));
         return true;
      } else {
         LOG.error("Unable to update endpoint. For more information see logs.");
         return false;
      }
   }

   private static String determineEndpoint() {
      String env = ConfigFactory.getConfigValidator().getProperty("environment", "prd");
      String endpoint = ConfigFactory.getConfigValidator().getProperty("be.ehealth.technicalconnector.bcp.status.endpoint");
      if (StringUtils.isNotBlank(endpoint)) {
         return endpoint;
      } else if ("prd".equals(env)) {
         return "https://servicelist.ehealth.fgov.be/servicelist";
      } else if ("acc".equals(env)) {
         return "https://servicelist-acpt.ehealth.fgov.be/servicelist";
      } else if ("int".equals(env)) {
         return "https://bcp-int.ehealth.fgov.be/current_status";
      } else {
         throw new IllegalArgumentException("Unsupported Environment [" + env + "]");
      }
   }

   public static void reset() {
      delete(loadedSha2Location);
      delete(loadedXmlLocation);
      loadedSha2 = null;
      EndpointDistributor.getInstance().reset();
   }

   private static void delete(String location) {
      File file = new File(location);
      if (file.delete()) {
         LOG.debug("File [{}] deleted.", file.getName());
      } else {
         LOG.debug("Unable to delete [{}].", file.getName());
      }

   }

   static {
      try {
         if (ConfigFactory.getConfigValidator().hasProperty("bcp.local.cache.dir")) {
            String basePath = ConfigFactory.getConfigValidator().getProperty("bcp.local.cache.dir");
            loadedSha2Location = basePath + EndpointUpdater.class.getCanonicalName() + ".sha2";
            loadedXmlLocation = basePath + EndpointUpdater.class.getCanonicalName() + ".xml";
         } else {
            loadedSha2Location = ConnectorIOUtils.getTempFileLocation(EndpointUpdater.class.getCanonicalName() + ".sha2");
            loadedXmlLocation = ConnectorIOUtils.getTempFileLocation(EndpointUpdater.class.getCanonicalName() + ".xml");
         }

         if (update(ConnectorIOUtils.getResourceAsString(loadedXmlLocation))) {
            loadedSha2 = ConnectorIOUtils.getResourceAsString(loadedSha2Location);
         }
      } catch (Exception e) {
         LOG.error("Unable to load endpoints", e);
      }

   }
}
