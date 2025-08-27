package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreateRelationRequestType",
   propOrder = {"securedCreateRelationRequest"}
)
@XmlRootElement(
   name = "CreateRelationRequest"
)
public class CreateRelationRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreateRelationRequest",
      required = true
   )
   protected SecuredContentType securedCreateRelationRequest;

   public SecuredContentType getSecuredCreateRelationRequest() {
      return this.securedCreateRelationRequest;
   }

   public void setSecuredCreateRelationRequest(SecuredContentType value) {
      this.securedCreateRelationRequest = value;
   }
}
