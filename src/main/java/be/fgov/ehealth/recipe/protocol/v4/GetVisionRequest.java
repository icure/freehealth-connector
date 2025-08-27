package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetVisionRequestType",
   propOrder = {"securedGetVisionRequest"}
)
@XmlRootElement(
   name = "GetVisionRequest"
)
public class GetVisionRequest extends RequestType {
   @XmlElement(
      name = "SecuredGetVisionRequest",
      required = true
   )
   protected SecuredContentType securedGetVisionRequest;

   public SecuredContentType getSecuredGetVisionRequest() {
      return this.securedGetVisionRequest;
   }

   public void setSecuredGetVisionRequest(SecuredContentType value) {
      this.securedGetVisionRequest = value;
   }
}
