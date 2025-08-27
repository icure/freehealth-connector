package be.fgov.ehealth.commons.protocol.v1;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ResponseType createResponseType() {
      return new ResponseType();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }
}
