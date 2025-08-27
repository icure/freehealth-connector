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
   name = "RevokePrescriptionResponseType",
   propOrder = {"securedRevokePrescriptionResponse"}
)
@XmlRootElement(
   name = "RevokePrescriptionResponse"
)
public class RevokePrescriptionResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredRevokePrescriptionResponse"
   )
   protected SecuredContentType securedRevokePrescriptionResponse;

   public SecuredContentType getSecuredRevokePrescriptionResponse() {
      return this.securedRevokePrescriptionResponse;
   }

   public void setSecuredRevokePrescriptionResponse(SecuredContentType value) {
      this.securedRevokePrescriptionResponse = value;
   }
}
