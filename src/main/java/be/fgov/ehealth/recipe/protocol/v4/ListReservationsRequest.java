package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListReservationsRequestType",
   propOrder = {"securedListReservationsRequest"}
)
@XmlRootElement(
   name = "ListReservationsRequest"
)
public class ListReservationsRequest extends RequestType {
   @XmlElement(
      name = "SecuredListReservationsRequest",
      required = true
   )
   protected SecuredContentType securedListReservationsRequest;

   public SecuredContentType getSecuredListReservationsRequest() {
      return this.securedListReservationsRequest;
   }

   public void setSecuredListReservationsRequest(SecuredContentType value) {
      this.securedListReservationsRequest = value;
   }
}
