package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePatientPrescriptionRequestType",
   propOrder = {"securedRevokePatientPrescriptionRequest"}
)
@XmlRootElement(
   name = "RevokePatientPrescriptionRequest"
)
public class RevokePatientPrescriptionRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredRevokePatientPrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedRevokePatientPrescriptionRequest;

   public SecuredContentType getSecuredRevokePatientPrescriptionRequest() {
      return this.securedRevokePatientPrescriptionRequest;
   }

   public void setSecuredRevokePatientPrescriptionRequest(SecuredContentType value) {
      this.securedRevokePatientPrescriptionRequest = value;
   }
}
