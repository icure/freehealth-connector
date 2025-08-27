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
   name = "ListRidsInProcessResponseType",
   propOrder = {"securedListRidsInProcessResponse"}
)
@XmlRootElement(
   name = "ListRidsInProcessResponse"
)
public class ListRidsInProcessResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListRidsInProcessResponse"
   )
   protected SecuredContentType securedListRidsInProcessResponse;

   public SecuredContentType getSecuredListRidsInProcessResponse() {
      return this.securedListRidsInProcessResponse;
   }

   public void setSecuredListRidsInProcessResponse(SecuredContentType value) {
      this.securedListRidsInProcessResponse = value;
   }
}
