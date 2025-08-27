package be.fgov.ehealth.rn.cbsspersonservice.protocol.v1;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public RegisterPersonRequest createRegisterPersonRequest() {
      return new RegisterPersonRequest();
   }

   public RegisterPersonResponse createRegisterPersonResponse() {
      return new RegisterPersonResponse();
   }
}
