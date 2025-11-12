package be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl;

import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.regional.MemberDataIrisCareNetService;
import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.regional.MemberDataWalCareNetService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class MemberDataServiceImplementationFactory extends ImplementationClassFactory {
   public MemberDataServiceImplementationFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(MemberDataMyCareNetService.class) && additionalParameters.length == 0) {
         return (T)(new MemberDataMyCareNetService(sessionValidator));
      } else if (clazz.equals(MemberDataIrisCareNetService.class) && additionalParameters.length == 0) {
         return (T)(new MemberDataIrisCareNetService(sessionValidator));
      } else if (clazz.equals(MemberDataWalCareNetService.class) && additionalParameters.length == 0) {
         return (T)(new MemberDataWalCareNetService(sessionValidator));
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
