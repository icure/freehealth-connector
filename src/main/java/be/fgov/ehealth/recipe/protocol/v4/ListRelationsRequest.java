package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListRelationsRequestType",
   propOrder = {"securedListRelationsRequest"}
)
@XmlRootElement(
   name = "ListRelationsRequest"
)
public class ListRelationsRequest extends RequestType {
   @XmlElement(
      name = "SecuredListRelationsRequest",
      required = true
   )
   protected SecuredContentType securedListRelationsRequest;

   public SecuredContentType getSecuredListRelationsRequest() {
      return this.securedListRelationsRequest;
   }

   public void setSecuredListRelationsRequest(SecuredContentType value) {
      this.securedListRelationsRequest = value;
   }
}
