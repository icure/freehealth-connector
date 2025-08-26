package be.ehealth.businessconnector.mycarenet.memberdatav2.builders.regional;

import be.ehealth.businessconnector.mycarenet.memberdatacommons.builders.RequestObjectBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class WalCareNetRequestObjectBuilderFactory {
   private static final String PROP_ENCRYPTEDREQUESTBUILDER_CLASS = "walcarenet.encryptedrequestobjectbuilder.class";
   private static final String PROP_NOTENCRYPTEDREQUESTBUILDER_CLASS = "walcarenet.notencryptedrequestobjectbuilder.class";
   private static final String DEFAULT_ENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.WalCareNetEncryptedRequestObjectBuilder";
   private static final String DEFAULT_NOTENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.WalCareNetNotEncryptedRequestObjectBuilder";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryERequestBuilder = new ConfigurableFactoryHelper<RequestObjectBuilder>("walcarenet.encryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.WalCareNetEncryptedRequestObjectBuilder");
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryNERequestBuilder = new ConfigurableFactoryHelper<RequestObjectBuilder>("walcarenet.notencryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.WalCareNetNotEncryptedRequestObjectBuilder");

   private WalCareNetRequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getEncryptedRequestObjectBuilder() throws TechnicalConnectorException {
      return helperFactoryERequestBuilder.getImplementation();
   }

   public static RequestObjectBuilder getNotEncryptedRequestObjectBuilder() throws TechnicalConnectorException {
      return helperFactoryNERequestBuilder.getImplementation();
   }
}
