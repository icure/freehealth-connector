package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionStatusRequestType",
   propOrder = {"securedGetPrescriptionStatusRequest"}
)
@XmlRootElement(
   name = "GetPrescriptionStatusRequest"
)
public class GetPrescriptionStatusRequest extends RequestType {
   @XmlElement(
      name = "SecuredGetPrescriptionStatusRequest",
      required = true
   )
   protected SecuredContentType securedGetPrescriptionStatusRequest;

   public SecuredContentType getSecuredGetPrescriptionStatusRequest() {
      return this.securedGetPrescriptionStatusRequest;
   }

   public void setSecuredGetPrescriptionStatusRequest(SecuredContentType value) {
      this.securedGetPrescriptionStatusRequest = value;
   }
}
