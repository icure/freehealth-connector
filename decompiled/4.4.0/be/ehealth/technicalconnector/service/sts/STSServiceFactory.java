package be.ehealth.technicalconnector.service.sts;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.impl.STSServiceWsTrustImpl;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class STSServiceFactory {
   private static final String PROP_STSSERVICE_CLASS = "service.sts.class";
   private static final String DEFAULT_STSSERVICE_CLASS = STSServiceWsTrustImpl.class.getName();
   private static final ConfigurableFactoryHelper<STSService> factoryHelper;

   private STSServiceFactory() {
   }

   public static STSService getInstance() throws TechnicalConnectorException {
      return factoryHelper.getImplementation();
   }

   static {
      factoryHelper = new ConfigurableFactoryHelper<STSService>("service.sts.class", DEFAULT_STSSERVICE_CLASS);
   }
}
