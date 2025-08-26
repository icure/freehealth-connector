package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.SilentInstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.fedict.commons.eid.jca.BeIDProvider;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.ec.SunEC;

public class ConfigurationModuleSecurityProvider implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSecurityProvider.class);
   private static final String PROP_CONFIGURATIONMODULESECURITYPROVIDER_LOADED = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider";
   private List<Provider> providersAdded = new ArrayList();
   public static final String PROP_CLEANUP_ACTION_KEY = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.cleanup.action";
   public static final String PROP_CLEANUP_ACTION_PARTIAL_ROOTKEY = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.cleanup.action.partial";
   public static final String PROP_ADD_SECURITYPROVIDERS_ROOTKEY = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders";
   public static final String PROP_ADD_SECURITYPROVIDERS_ACTIVATED = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.activated";
   public static final String PROP_ADD_SECURITYPROVIDERS_POSITION_KEY = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.insertProviderAt";

   public ConfigurationModuleSecurityProvider() {
   }

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      String executed = System.getProperty("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider", "false");
      if ("false".equalsIgnoreCase(executed)) {
         this.removeSecurityProviders(config.getProperty("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.cleanup.action", "none"), config);
         this.addSecurityProviders(config);
         this.printCurrentSecurityProviders();
         System.setProperty("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider", "true");
      }

   }

   private void printCurrentSecurityProviders() {
      if (LOG.isDebugEnabled()) {
         Provider[] providers = Security.getProviders();
         LOG.debug("Overview of security providers:");

         for(Provider prov : providers) {
            LOG.debug("\t. {} [{}]", prov.getName(), prov.getClass().getName());
         }
      }

   }

   private void addSecurityProviders(Configuration config) {
      String action = config.getProperty("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.activated");
      if (StringUtils.isEmpty(action)) {
         Security.removeProvider("SunEC");
         Security.addProvider(new BouncyCastleProvider());
         Security.addProvider(new BeIDProvider());
         Security.addProvider(new SunEC());
      } else if ("true".equalsIgnoreCase(action)) {
         try {
            ConfigurableFactoryHelper<Provider> helper = new ConfigurableFactoryHelper<Provider>("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders", (String)null);
            List<Provider> providerList = helper.getImplementations();
            String position = config.getProperty("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.insertProviderAt", "end");

            for(Provider provider : providerList) {
               this.removeSecurityProvider(provider.getName());
               if ("end".equals(position)) {
                  LOG.debug("Inserting provider {}", provider.getName());
                  Security.addProvider(provider);
               } else if ("begin".equals(position)) {
                  LOG.debug("Inserting provider {} at position 1.", provider.getName());
                  Security.insertProviderAt(provider, 1);
               } else if (StringUtils.isNumeric(position)) {
                  Integer positionId = Integer.parseInt(position);
                  LOG.debug("Inserting provider {} at position {}", provider.getName(), positionId);
                  Security.insertProviderAt(provider, positionId);
               } else {
                  LOG.warn("Unsupported position value [" + position + "]");
               }
            }
         } catch (TechnicalConnectorException e) {
            LOG.error("{}: {}", new Object[]{e.getClass().getSimpleName(), e.getMessage(), e});
         }
      }

   }

   private void removeSecurityProviders(String action, Configuration config) {
      if ("full".equals(action)) {
         Provider[] providers = Security.getProviders();

         for(Provider prov : providers) {
            this.removeSecurityProvider(prov.getName());
         }
      } else if ("partial".equals(action)) {
         for(String providerName : config.getMatchingProperties("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider.cleanup.action.partial")) {
            this.removeSecurityProvider(providerName);
         }
      }

   }

   private void removeSecurityProvider(String providerName) {
      LOG.debug("Removing SecurityProvider with Name [{}]", providerName);
      Security.removeProvider(providerName);
   }

   public void unload() throws TechnicalConnectorException {
      for(Provider provider : this.providersAdded) {
         LOG.debug("Removing provider {}", provider.getName());
         Security.removeProvider(provider.getName());
      }

      System.setProperty("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSecurityProvider", "false");
   }

   private Provider instantiate(String className) throws SilentInstantiationException {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());

      try {
         Class clazz = Class.forName(className);
         return (Provider)clazz.newInstance();
      } catch (ClassNotFoundException e) {
         throw new SilentInstantiationException(e);
      } catch (InstantiationException e) {
         throw new SilentInstantiationException(e);
      } catch (IllegalAccessException e) {
         throw new SilentInstantiationException(e);
      }
   }
}
