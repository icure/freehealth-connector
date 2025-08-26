package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleVersion implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleVersion.class);

   public ConfigurationModuleVersion() {
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      if (LOG.isDebugEnabled()) {
         this.dumpJarFromCP();
         this.dumpServiceLoaderFiles();
      }

   }

   public void dumpJarFromCP() {
      try {
         Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
         String[] cpElements = ArrayUtils.EMPTY_STRING_ARRAY;

         while(resEnum.hasMoreElements()) {
            URL url = (URL)resEnum.nextElement();
            StringBuilder sb = new StringBuilder("[CP Content] ");
            String substringAfterLast = StringUtils.substringAfterLast(StringUtils.substringBefore(url.getPath(), "!"), "/");
            if (!"MANIFEST.MF".equals(substringAfterLast)) {
               sb.append(substringAfterLast);
               cpElements = (String[])ArrayUtils.add(cpElements, sb.toString());
            }
         }

         Arrays.sort(cpElements);

         for(String cpElement : cpElements) {
            LOG.debug(cpElement);
         }
      } catch (IOException e) {
         LOG.error(e.getMessage(), e);
      }

   }

   public void dumpServiceLoaderFiles() {
      try {
         Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/services");

         String[] cpElements;
         StringBuilder sb;
         for(cpElements = ArrayUtils.EMPTY_STRING_ARRAY; resEnum.hasMoreElements(); cpElements = (String[])ArrayUtils.add(cpElements, sb.toString())) {
            URL url = new URL(StringUtils.substringBeforeLast(((URL)resEnum.nextElement()).getPath(), "!"));
            sb = new StringBuilder("[JAR with ServiceLoader] ");
            sb.append(StringUtils.substringAfterLast(url.getPath(), "/"));
            List<String> serviceServiceLoaderFiles = this.extractServiceLoaderFiles(url);
            sb.append(" (");

            for(int i = 0; i < serviceServiceLoaderFiles.size(); ++i) {
               if (i != 0) {
                  sb.append(", ");
               }

               sb.append((String)serviceServiceLoaderFiles.get(i));
            }

            sb.append(")");
         }

         Arrays.sort(cpElements);

         for(String cpElement : cpElements) {
            LOG.debug(cpElement);
         }
      } catch (IOException e) {
         LOG.error(e.getMessage(), e);
      }

   }

   private List<String> extractServiceLoaderFiles(URL url) throws IOException {
      List<String> result = new ArrayList();
      ZipInputStream zip = new ZipInputStream(url.openStream());
      ZipEntry ze = null;

      while((ze = zip.getNextEntry()) != null) {
         String entryName = ze.getName();
         if (entryName.contains("META-INF/services")) {
            String file = StringUtils.substringAfterLast(entryName, "/");
            if (StringUtils.isNotBlank(file)) {
               result.add(file);
            }
         }
      }

      Collections.sort(result);
      return result;
   }

   public void unload() throws TechnicalConnectorException {
   }
}
