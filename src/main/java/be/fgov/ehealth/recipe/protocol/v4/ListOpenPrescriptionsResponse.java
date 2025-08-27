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
   name = "ListOpenPrescriptionsResponseType",
   propOrder = {"securedListOpenPrescriptionsResponse"}
)
@XmlRootElement(
   name = "ListOpenPrescriptionsResponse"
)
public class ListOpenPrescriptionsResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListOpenPrescriptionsResponse"
   )
   protected SecuredContentType securedListOpenPrescriptionsResponse;

   public SecuredContentType getSecuredListOpenPrescriptionsResponse() {
      return this.securedListOpenPrescriptionsResponse;
   }

   public void setSecuredListOpenPrescriptionsResponse(SecuredContentType value) {
      this.securedListOpenPrescriptionsResponse = value;
   }
}
