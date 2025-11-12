package be.ehealth.technicalconnector.beid;

import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.beid.impl.CommonsEidInstantiator;
import be.ehealth.technicalconnector.config.impl.ConfigUtils;
import be.ehealth.technicalconnector.exception.InstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.security.KeyStore;

public class BeIDFactory {
   private static final String PROP_BEID_INSTANTIATOR = "be.ehealth.technicalconnector.beid.instantiator";
   private static ConfigurableFactoryHelper<BeIDInstantiator> helper = new ConfigurableFactoryHelper<BeIDInstantiator>("be.ehealth.technicalconnector.beid.instantiator", getDefaultInstantiator());
   private static BeIDInstantiator instantiator;

   public BeIDFactory() {
   }

   private static String getDefaultInstantiator() {
      try {
         return ConfigUtils.isNet() ? "be.fgov.ehealth.technicalconnector.beid.BeIDNetInstantiator" : CommonsEidInstantiator.class.getName();
      } catch (TechnicalConnectorException e) {
         throw new InstantiationException("Unable to determine default Instantiator", e);
      }
   }

   private static BeIDInstantiator getInstantiator() throws TechnicalConnectorException {
      if (instantiator == null) {
         BeIDInstantiator beIDInstantiator = helper.getImplementation();
         validate(beIDInstantiator);
         instantiator = beIDInstantiator;
      }

      return instantiator;
   }

   private static void validate(BeIDInstantiator beIDInstantiator) {
      try {
         if (ConfigUtils.isNet() && !beIDInstantiator.worksWithDotNet()) {
            throw new InstantiationException(".NET detected: Please use a correct BeIDInstantiator.", new IllegalArgumentException("Unsupported BeIDInstantiator [" + beIDInstantiator.getClass() + "]"));
         }
      } catch (TechnicalConnectorException e) {
         throw new InstantiationException("Unable to validate BeIDInstantiator", e);
      }
   }

   public static void setInstantiator(BeIDInstantiator instantiator) {
      validate(instantiator);
      BeIDFactory.instantiator = instantiator;
   }

   public static BeIDInfo getBeIDInfo(String scope, boolean useCache) throws TechnicalConnectorException {
      return getInstantiator().instantiateBeIDInfo(scope, useCache);
   }

   public static KeyStore getKeyStore(String scope, boolean useCache) throws TechnicalConnectorException {
      return getInstantiator().instantiateKeyStore(scope, useCache);
   }

   public static void verify(char[] pin) throws TechnicalConnectorException {
      getInstantiator().verifyPin(pin);
   }
}
