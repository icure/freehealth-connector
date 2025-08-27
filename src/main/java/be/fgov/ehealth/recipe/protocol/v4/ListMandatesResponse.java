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
   name = "ListMandatesResponseType",
   propOrder = {"securedListMandatesResponse"}
)
@XmlRootElement(
   name = "ListMandatesResponse"
)
public class ListMandatesResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListMandatesResponse"
   )
   protected SecuredContentType securedListMandatesResponse;

   public SecuredContentType getSecuredListMandatesResponse() {
      return this.securedListMandatesResponse;
   }

   public void setSecuredListMandatesResponse(SecuredContentType value) {
      this.securedListMandatesResponse = value;
   }
}
