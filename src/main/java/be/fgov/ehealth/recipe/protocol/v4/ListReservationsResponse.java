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
   name = "ListReservationsResponseType",
   propOrder = {"securedListReservationsResponse"}
)
@XmlRootElement(
   name = "ListReservationsResponse"
)
public class ListReservationsResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListReservationsResponse"
   )
   protected SecuredContentType securedListReservationsResponse;

   public SecuredContentType getSecuredListReservationsResponse() {
      return this.securedListReservationsResponse;
   }

   public void setSecuredListReservationsResponse(SecuredContentType value) {
      this.securedListReservationsResponse = value;
   }
}
