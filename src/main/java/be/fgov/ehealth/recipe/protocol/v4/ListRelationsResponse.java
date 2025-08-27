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
   name = "ListRelationsResponseType",
   propOrder = {"securedListRelationsResponse"}
)
@XmlRootElement(
   name = "ListRelationsResponse"
)
public class ListRelationsResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListRelationsResponse"
   )
   protected SecuredContentType securedListRelationsResponse;

   public SecuredContentType getSecuredListRelationsResponse() {
      return this.securedListRelationsResponse;
   }

   public void setSecuredListRelationsResponse(SecuredContentType value) {
      this.securedListRelationsResponse = value;
   }
}
