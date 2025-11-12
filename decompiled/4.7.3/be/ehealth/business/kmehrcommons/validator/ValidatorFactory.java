package be.ehealth.business.kmehrcommons.validator;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class ValidatorFactory {
   public static final String PROP_KMEHRVALIDATOR_CLASS = "kmehrvalidator.class";
   public static final String DEFAULT_KMEHRVALIDATOR_CLASS = "be.ehealth.business.kmehrcommons.validator.impl.KmehrValidatorImpl";
   private static ConfigurableFactoryHelper<KmehrValidator> helperFactoryKmehrValidator = new ConfigurableFactoryHelper<KmehrValidator>("kmehrvalidator.class", "be.ehealth.business.kmehrcommons.validator.impl.KmehrValidatorImpl");

   private ValidatorFactory() {
      throw new UnsupportedOperationException();
   }

   public static KmehrValidator getKmehrValidator() throws TechnicalConnectorException {
      return helperFactoryKmehrValidator.getImplementation();
   }
}
