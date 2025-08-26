package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleLoader {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleLoader.class);
   private static final String CONNECTOR_CONFIGMODULES = "connector.configmodule";
   private static List<ConfigurationModule> modulesRegistry = new ArrayList();

   private ConfigurationModuleLoader() {
      throw new UnsupportedOperationException();
   }

   public static void load(Configuration instance) throws TechnicalConnectorException {
      LOG.debug("Loading ConfigurationModule");
      Validate.notNull(instance);
      ConfigurableFactoryHelper<ConfigurationModule> helper = new ConfigurableFactoryHelper<ConfigurationModule>("connector.configmodule", (String)null);
      List<ConfigurationModule> modulesToLoad = helper.getImplementations(true, true);
      filter(modulesToLoad);
      List<ConfigurationModule> modules = new ArrayList();
      modules.add(new ConfigurationModuleClassloader());
      modules.add(new ConfigurationModuleBeidNet());
      modules.add(new ConfigurationModuleLogging());
      modules.add(new ConfigurationModuleVersion());
      modules.add(new ConfigurationModuleProperties());
      modules.addAll(modulesToLoad);
      modules.add(new ConfigurationModuleSecurityProvider());
      modules.add(new ConfigurationModuleTrustStore());
      modules.add(new ConfigurationModuleEndpointDistributor());
      modules.add(new ConfigurationModuleOCSP());
      modules.add(new ConfigurationModuleEhealthTime());
      modulesRegistry.clear();

      for(ConfigurationModule module : modules) {
         if (Boolean.parseBoolean(System.getProperty("be.ehealth.technicalconnector.config.impl.Configuration.use." + module.getClass().getSimpleName(), "true"))) {
            modulesRegistry.add(module);
            module.init(instance);
         }
      }

   }

   static void filter(List<ConfigurationModule> modulesToLoad) {
      CollectionUtils.filter(modulesToLoad, new ConfigurationModulePredicate(new Class[]{ConfigurationModuleClassloader.class, ConfigurationModuleLogging.class, ConfigurationModuleVersion.class, ConfigurationModuleProperties.class, ConfigurationModuleSecurityProvider.class, ConfigurationModuleTrustStore.class, ConfigurationModuleOCSP.class}));
   }

   static void unload() {
      for(ConfigurationModule module : modulesRegistry) {
         try {
            module.unload();
         } catch (TechnicalConnectorException e) {
            LOG.error(e.getClass().getSimpleName() + ":" + e.getMessage(), e);
         }
      }

   }

   static void unloadSystemProperties(Map<String, String> oldValues) {
      for(Map.Entry<String, String> entry : oldValues.entrySet()) {
         LOG.info("Resetting key [{}] for value[{}]", entry.getKey(), entry.getValue());
         String oldValue = (String)oldValues.get(entry.getKey());
         if (oldValue == null) {
            System.getProperties().remove(entry.getKey());
         } else {
            System.setProperty((String)entry.getKey(), (String)entry.getValue());
         }
      }

   }

   private static class ConfigurationModulePredicate implements Predicate {
      private Class[] clazz;

      public ConfigurationModulePredicate(Class<? extends ConfigurationModule>... clazz) {
         this.clazz = clazz;
      }

      public boolean evaluate(Object object) {
         return !ArrayUtils.contains(this.clazz, object.getClass());
      }
   }
}
