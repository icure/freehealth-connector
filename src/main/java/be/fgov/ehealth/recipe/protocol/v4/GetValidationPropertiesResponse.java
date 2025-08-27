package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetValidationPropertiesResponseType",
   propOrder = {"securedGetValidationPropertiesResponse"}
)
@XmlRootElement(
   name = "GetValidationPropertiesResponse"
)
public class GetValidationPropertiesResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredGetValidationPropertiesResponse"
   )
   protected SecuredContentType securedGetValidationPropertiesResponse;

   public SecuredContentType getSecuredGetValidationPropertiesResponse() {
      return this.securedGetValidationPropertiesResponse;
   }

   public void setSecuredGetValidationPropertiesResponse(SecuredContentType value) {
      this.securedGetValidationPropertiesResponse = value;
   }
}
