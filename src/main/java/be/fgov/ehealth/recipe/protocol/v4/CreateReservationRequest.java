package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreateReservationRequestType",
   propOrder = {"securedCreateReservationRequest"}
)
@XmlRootElement(
   name = "CreateReservationRequest"
)
public class CreateReservationRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreateReservationRequest",
      required = true
   )
   protected SecuredContentType securedCreateReservationRequest;

   public SecuredContentType getSecuredCreateReservationRequest() {
      return this.securedCreateReservationRequest;
   }

   public void setSecuredCreateReservationRequest(SecuredContentType value) {
      this.securedCreateReservationRequest = value;
   }
}
