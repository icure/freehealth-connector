package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationWithSessionCache extends AbstractConfigurationImpl implements SessionServiceWithCache {
   private final Configuration configuration;
   private final Map<String, String> originalProperties = new HashMap();

   public ConfigurationWithSessionCache(Configuration configuration) {
      this.configuration = configuration;
      Session.getInstance().registerSessionService(this);
   }

   public String getProperty(String key, String defaultValue) {
      return this.configuration.getProperty(key, defaultValue);
   }

   public void setProperty(String key, String value) {
      this.originalProperties.put(key, this.configuration.getProperty(key));
      this.configuration.setProperty(key, value);
   }

   public boolean hasProperty(String key) {
      return this.configuration.hasProperty(key);
   }

   public Configuration getCurrentConfig() throws TechnicalConnectorException {
      return this;
   }

   public void invalidate() throws TechnicalConnectorException {
      this.configuration.invalidate();
   }

   public void reload() throws TechnicalConnectorException {
      this.configuration.reload();
   }

   public boolean isReloading() {
      return this.configuration.isReloading();
   }

   public void flushCache() {
      for(Map.Entry<String, String> entry : this.originalProperties.entrySet()) {
         this.configuration.setProperty((String)entry.getKey(), (String)entry.getValue());
      }

   }
}
