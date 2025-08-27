package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionRequestType",
   propOrder = {"securedGetPrescriptionRequest"}
)
@XmlRootElement(
   name = "GetPrescriptionRequest"
)
public class GetPrescriptionRequest extends RequestType {
   @XmlElement(
      name = "SecuredGetPrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedGetPrescriptionRequest;

   public SecuredContentType getSecuredGetPrescriptionRequest() {
      return this.securedGetPrescriptionRequest;
   }

   public void setSecuredGetPrescriptionRequest(SecuredContentType value) {
      this.securedGetPrescriptionRequest = value;
   }
}
