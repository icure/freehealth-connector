package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListRidsInProcessRequestType",
   propOrder = {"securedListRidsInProcessRequest"}
)
@XmlRootElement(
   name = "ListRidsInProcessRequest"
)
public class ListRidsInProcessRequest extends RequestType {
   @XmlElement(
      name = "SecuredListRidsInProcessRequest",
      required = true
   )
   protected SecuredContentType securedListRidsInProcessRequest;

   public SecuredContentType getSecuredListRidsInProcessRequest() {
      return this.securedListRidsInProcessRequest;
   }

   public void setSecuredListRidsInProcessRequest(SecuredContentType value) {
      this.securedListRidsInProcessRequest = value;
   }
}
