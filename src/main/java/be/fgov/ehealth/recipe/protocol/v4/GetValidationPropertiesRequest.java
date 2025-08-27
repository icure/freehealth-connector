package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetValidationPropertiesRequestType",
   propOrder = {"securedGetValidationPropertiesRequest"}
)
@XmlRootElement(
   name = "GetValidationPropertiesRequest"
)
public class GetValidationPropertiesRequest extends RequestType {
   @XmlElement(
      name = "SecuredGetValidationPropertiesRequest",
      required = true
   )
   protected SecuredContentType securedGetValidationPropertiesRequest;

   public SecuredContentType getSecuredGetValidationPropertiesRequest() {
      return this.securedGetValidationPropertiesRequest;
   }

   public void setSecuredGetValidationPropertiesRequest(SecuredContentType value) {
      this.securedGetValidationPropertiesRequest = value;
   }
}
