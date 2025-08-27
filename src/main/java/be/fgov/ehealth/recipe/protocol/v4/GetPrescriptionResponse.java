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
   name = "GetPrescriptionResponseType",
   propOrder = {"securedGetPrescriptionResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionResponse"
)
public class GetPrescriptionResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredGetPrescriptionResponse"
   )
   protected SecuredContentType securedGetPrescriptionResponse;

   public SecuredContentType getSecuredGetPrescriptionResponse() {
      return this.securedGetPrescriptionResponse;
   }

   public void setSecuredGetPrescriptionResponse(SecuredContentType value) {
      this.securedGetPrescriptionResponse = value;
   }
}
