package be.ehealth.businessconnector.mycarenet.memberdatav2.builders.regional;

import be.ehealth.businessconnector.mycarenet.memberdatacommons.builders.RequestObjectBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class IrisCareNetRequestObjectBuilderFactory {
   private static final String PROP_ENCRYPTEDREQUESTBUILDER_CLASS = "iriscarenet.encryptedrequestobjectbuilder.class";
   private static final String PROP_NOTENCRYPTEDREQUESTBUILDER_CLASS = "iriscarenet.notencryptedrequestobjectbuilder.class";
   private static final String DEFAULT_ENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.IrisCareNetEncryptedRequestObjectBuilder";
   private static final String DEFAULT_NOTENCRYPTEDREQUESTBUILDER_CLASS = "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.IrisCareNetNotEncryptedRequestObjectBuilder";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryERequestBuilder = new ConfigurableFactoryHelper<RequestObjectBuilder>("iriscarenet.encryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.IrisCareNetEncryptedRequestObjectBuilder");
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryNERequestBuilder = new ConfigurableFactoryHelper<RequestObjectBuilder>("iriscarenet.notencryptedrequestobjectbuilder.class", "be.ehealth.businessconnector.mycarenet.memberdatav2.builders.impl.regional.IrisCareNetNotEncryptedRequestObjectBuilder");

   private IrisCareNetRequestObjectBuilderFactory() {
   }

   public static RequestObjectBuilder getEncryptedRequestObjectBuilder() throws TechnicalConnectorException {
      return helperFactoryERequestBuilder.getImplementation();
   }

   public static RequestObjectBuilder getNotEncryptedRequestObjectBuilder() throws TechnicalConnectorException {
      return helperFactoryNERequestBuilder.getImplementation();
   }
}
