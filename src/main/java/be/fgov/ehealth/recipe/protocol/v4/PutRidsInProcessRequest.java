package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutRidsInProcessRequestType",
   propOrder = {"securedPutRidsInProcessRequest"}
)
@XmlRootElement(
   name = "PutRidsInProcessRequest"
)
public class PutRidsInProcessRequest extends RequestType {
   @XmlElement(
      name = "SecuredPutRidsInProcessRequest",
      required = true
   )
   protected SecuredContentType securedPutRidsInProcessRequest;

   public SecuredContentType getSecuredPutRidsInProcessRequest() {
      return this.securedPutRidsInProcessRequest;
   }

   public void setSecuredPutRidsInProcessRequest(SecuredContentType value) {
      this.securedPutRidsInProcessRequest = value;
   }
}
