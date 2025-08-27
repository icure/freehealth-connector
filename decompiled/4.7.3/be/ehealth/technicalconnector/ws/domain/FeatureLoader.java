package be.ehealth.technicalconnector.ws.domain;

import be.ehealth.technicalconnector.ws.feature.GenericFeature;
import java.util.HashMap;
import java.util.Map;

public class FeatureLoader {
   private Map<Class, GenericFeature> activeFeatures = new HashMap();

   public FeatureLoader() {
   }

   public <T extends GenericFeature> T getFeature(Class<T> clazz) {
      for(Map.Entry<Class, GenericFeature> entry : this.activeFeatures.entrySet()) {
         if (clazz.isAssignableFrom((Class)entry.getKey())) {
            return (T)(entry.getValue());
         }
      }

      return (T)(this.activeFeatures.get(clazz));
   }

   public boolean hasFeature(Class<?> clazz) {
      for(Class key : this.activeFeatures.keySet()) {
         if (clazz.isAssignableFrom(key)) {
            return true;
         }
      }

      return this.activeFeatures.containsKey(clazz);
   }

   public void register(GenericFeature... features) {
      for(GenericFeature feature : features) {
         if (feature != null) {
            this.activeFeatures.put(feature.getClass(), feature);
         }
      }

   }

   public HandlerChain getHandlerChain() {
      HandlerChain handlerChain = new HandlerChain();

      for(GenericFeature feature : this.activeFeatures.values()) {
         handlerChain.add(feature.getHandlers());
      }

      return handlerChain;
   }

   public Map<String, Object> getRequestMap() {
      Map<String, Object> requestMap = new HashMap();

      for(GenericFeature feature : this.activeFeatures.values()) {
         if (feature != null) {
            requestMap.put(feature.getID(), feature.isEnabled());
            requestMap.putAll(feature.requestParamOptions());
         }
      }

      return requestMap;
   }
}
