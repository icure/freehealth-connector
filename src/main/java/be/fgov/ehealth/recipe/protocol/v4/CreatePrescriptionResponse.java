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
   name = "CreatePrescriptionResponseType",
   propOrder = {"securedCreatePrescriptionResponse"}
)
@XmlRootElement(
   name = "CreatePrescriptionResponse"
)
public class CreatePrescriptionResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredCreatePrescriptionResponse"
   )
   protected SecuredContentType securedCreatePrescriptionResponse;

   public SecuredContentType getSecuredCreatePrescriptionResponse() {
      return this.securedCreatePrescriptionResponse;
   }

   public void setSecuredCreatePrescriptionResponse(SecuredContentType value) {
      this.securedCreatePrescriptionResponse = value;
   }
}
