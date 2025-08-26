package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.SilentInstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurableFactoryHelper<T> {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurableFactoryHelper.class);
   private final Map<CacheKey, T> cache = new HashMap();
   private final String classPropertyName;
   private final String defaultClassPropertyName;

   public ConfigurableFactoryHelper(String classPropertyName, String defaultClassPropertyName) {
      this.classPropertyName = classPropertyName;
      this.defaultClassPropertyName = defaultClassPropertyName;
   }

   private T createAndConfigureImplementation(String headerClassName, Map<String, Object> configParameters, boolean silent) throws TechnicalConnectorException {
      try {
         T result = (T)this.createInstance(headerClassName);
         if (result != null) {
            this.init(result, configParameters, silent);
         }

         return result;
      } catch (Exception e) {
         if (!silent) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.HEADER_INSTANCIATION, e, new Object[]{headerClassName});
         } else {
            return null;
         }
      }
   }

   private T createInstance(String headerClassName) throws ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
      Class<?> provider = Class.forName(headerClassName);
      if (provider.getAnnotation(Deprecated.class) != null) {
         LOG.debug("Instantiating a deprecated class [{}], please verify the javadoc!", headerClassName);
      }

      Object providerObject;
      try {
         providerObject = provider.newInstance();
      } catch (IllegalAccessException var6) {
         LOG.debug("Default constructor is not public. Trying to invoke getInstance().");
         Method method = provider.getMethod("getInstance");
         providerObject = method.invoke(provider);
      }

      return (T)providerObject;
   }

   public T getImplementation() throws TechnicalConnectorException {
      return (T)this.getImplementation(new HashMap(), true, false);
   }

   public T getImplementation(boolean useCache) throws TechnicalConnectorException {
      return (T)this.getImplementation(new HashMap(), useCache, false);
   }

   public T getImplementation(Map<String, Object> configParameters) throws TechnicalConnectorException {
      return (T)this.getImplementation(configParameters, true, false);
   }

   public T getImplementation(Map<String, Object> hashMap, boolean usecache) throws TechnicalConnectorException {
      return (T)this.getImplementation(hashMap, usecache, false);
   }

   public T getImplementation(Map<String, Object> configParameters, boolean useCaching, boolean silent) throws TechnicalConnectorException {
      String headerClassName = ConfigFactory.getConfigValidator().getProperty(this.classPropertyName, this.defaultClassPropertyName);
      CacheKey cacheKey = new CacheKey(configParameters, headerClassName);
      if (useCaching && this.cache.containsKey(cacheKey)) {
         return (T)this.cache.get(cacheKey);
      } else if (headerClassName == null && !silent) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"No valid configuration " + this.classPropertyName + " not found."});
      } else {
         T result = (T)this.getImplementation(headerClassName, configParameters, useCaching, silent);
         if (result == null && !silent) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"No valid configuration " + this.classPropertyName + " not found."});
         } else {
            return result;
         }
      }
   }

   private T getImplementation(String headerClassName, Map<String, Object> configParameters, boolean useCache, boolean silent) throws TechnicalConnectorException {
      CacheKey key = new CacheKey(configParameters, headerClassName);
      if (useCache && this.cache.containsKey(key)) {
         return (T)this.cache.get(key);
      } else if (headerClassName != null && !headerClassName.isEmpty()) {
         T result = (T)this.createAndConfigureImplementation(headerClassName, configParameters, silent);
         if (useCache) {
            this.cache.put(key, result);
         }

         return result;
      } else {
         return null;
      }
   }

   public List<T> getImplementations() throws TechnicalConnectorException {
      return this.getImplementations(true);
   }

   public List<T> getImplementations(boolean useCache) throws TechnicalConnectorException {
      return this.getImplementations(useCache, true);
   }

   public List<T> getImplementations(boolean useCache, boolean silent) throws TechnicalConnectorException {
      return this.getImplementations(new HashMap(), useCache, silent);
   }

   public List<T> getImplementations(Map<String, Object> configParameters) throws TechnicalConnectorException {
      return this.getImplementations(configParameters, true);
   }

   public List<T> getImplementations(Map<String, Object> configParameters, boolean useCache) throws TechnicalConnectorException {
      return this.getImplementations(configParameters, useCache, true);
   }

   public List<T> getImplementations(Map<String, Object> configParameters, boolean useCache, boolean silent) throws TechnicalConnectorException {
      List<T> result = new ArrayList();
      if (ConfigFactory.getConfigValidator().hasMatchingProperty(this.classPropertyName)) {
         for(String headerClassName : ConfigFactory.getConfigValidator().getMatchingProperties(this.classPropertyName)) {
            T resultItem = (T)this.getImplementation(headerClassName, configParameters, useCache, silent);
            if (resultItem != null) {
               result.add(resultItem);
            }
         }
      } else {
         T resultItem = (T)this.getImplementation(configParameters, useCache, silent);
         if (resultItem != null) {
            result.add(resultItem);
         }
      }

      return result;
   }

   private void init(T result, Map<String, Object> configParameters, boolean silent) throws TechnicalConnectorException {
      try {
         if (result instanceof ConfigurableImplementation) {
            if (configParameters == null) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"addConfigParamsIfNeeded : parameter configParameters is null!"});
            }

            ConfigurableImplementation resultAsConfigurable = (ConfigurableImplementation)result;
            resultAsConfigurable.initialize(configParameters);
         } else if (configParameters != null && !configParameters.isEmpty()) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"non configurable implementation " + result.getClass() + " called with non empty configParameters : the class should implement the interface ConfigurableImplementation to use configParameters with the ConfigurableFactoryHelper!"});
         }

      } catch (TechnicalConnectorException e) {
         if (!silent) {
            throw e;
         } else {
            throw new SilentInstantiationException(e);
         }
      }
   }

   public void invalidateCache() {
      this.cache.clear();
   }

   private static class CacheKey {
      private final String className;
      private final Map<String, Object> configProperties;

      public CacheKey(Map<String, Object> configProperties, String className) {
         this.configProperties = configProperties;
         this.className = className;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            CacheKey other = (CacheKey)obj;
            if (this.className == null) {
               if (other.className != null) {
                  return false;
               }
            } else if (!this.className.equals(other.className)) {
               return false;
            }

            if (this.configProperties == null) {
               return other.configProperties == null;
            } else {
               return this.configProperties.equals(other.configProperties);
            }
         }
      }

      public int hashCode() {
         int prime = 31;
         int result = 1;
         result = 31 * result + (this.className == null ? 0 : this.className.hashCode());
         result = 31 * result + (this.configProperties == null ? 0 : this.configProperties.hashCode());
         return result;
      }
   }
}
