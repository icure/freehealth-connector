package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokeRelationRequestType",
   propOrder = {"securedRevokeRelationRequest"}
)
@XmlRootElement(
   name = "RevokeRelationRequest"
)
public class RevokeRelationRequest extends RequestType {
   @XmlElement(
      name = "SecuredRevokeRelationRequest",
      required = true
   )
   protected SecuredContentType securedRevokeRelationRequest;

   public SecuredContentType getSecuredRevokeRelationRequest() {
      return this.securedRevokeRelationRequest;
   }

   public void setSecuredRevokeRelationRequest(SecuredContentType value) {
      this.securedRevokeRelationRequest = value;
   }
}
