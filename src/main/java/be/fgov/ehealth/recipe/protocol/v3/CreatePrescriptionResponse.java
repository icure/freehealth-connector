package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
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
public class CreatePrescriptionResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
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
