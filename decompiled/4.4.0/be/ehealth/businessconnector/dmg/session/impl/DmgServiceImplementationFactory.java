package be.ehealth.businessconnector.dmg.session.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class DmgServiceImplementationFactory extends ImplementationClassFactory {
   public DmgServiceImplementationFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(DmgServiceImpl.class) && additionalParameters.length == 0) {
         return (T)(new DmgServiceImpl(sessionValidator, replyValidator));
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
