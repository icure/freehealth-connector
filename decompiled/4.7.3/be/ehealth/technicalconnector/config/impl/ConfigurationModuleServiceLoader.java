package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.lang.reflect.Method;
import java.security.CodeSource;
import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleServiceLoader implements ConfigurationModule {
   public static final String SERVICELOADER_ROOTKEY = "configurationmodule.serviceloader";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleServiceLoader.class);

   public ConfigurationModuleServiceLoader() {
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      if (LOG.isDebugEnabled()) {
         for(String serviceLoader : config.getMatchingProperties("configurationmodule.serviceloader")) {
            String[] splittedServiceLoader = serviceLoader.split(":", 2);

            try {
               Class provider = Class.forName(splittedServiceLoader[0]);
               Object result = null;
               if (splittedServiceLoader.length == 1) {
                  Method method = provider.getMethod("newInstance");
                  result = method.invoke(provider);
               } else {
                  if (splittedServiceLoader.length != 2) {
                     LOG.debug("Unsupported serviceLoader value [{}].", serviceLoader);
                     break;
                  }

                  Method method = provider.getMethod("newInstance", String.class);
                  result = method.invoke(provider, splittedServiceLoader[1]);
               }

               CodeSource source = result.getClass().getProtectionDomain().getCodeSource();
               if (LOG.isDebugEnabled()) {
                  LOG.debug(MessageFormat.format("{0} implementation: {1} loaded from: {2}", splittedServiceLoader[0], result.getClass().getName(), source == null ? "Java Runtime" : source.getLocation()));
               }
            } catch (Exception e) {
               LOG.debug("{}: {}", new Object[]{e.getClass().getSimpleName(), e.getMessage(), e});
            }
         }

      }
   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());
   }
}
