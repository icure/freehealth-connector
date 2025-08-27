package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePrescriptionRequestType",
   propOrder = {"securedRevokePrescriptionRequest"}
)
@XmlRootElement(
   name = "RevokePrescriptionRequest"
)
public class RevokePrescriptionRequest extends RequestType {
   @XmlElement(
      name = "SecuredRevokePrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedRevokePrescriptionRequest;

   public SecuredContentType getSecuredRevokePrescriptionRequest() {
      return this.securedRevokePrescriptionRequest;
   }

   public void setSecuredRevokePrescriptionRequest(SecuredContentType value) {
      this.securedRevokePrescriptionRequest = value;
   }
}
