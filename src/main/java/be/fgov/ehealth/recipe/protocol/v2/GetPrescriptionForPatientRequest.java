package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForPatientRequestType",
   propOrder = {"securedGetPrescriptionForPatientRequest"}
)
@XmlRootElement(
   name = "GetPrescriptionForPatientRequest"
)
public class GetPrescriptionForPatientRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredGetPrescriptionForPatientRequest",
      required = true
   )
   protected SecuredContentType securedGetPrescriptionForPatientRequest;

   public SecuredContentType getSecuredGetPrescriptionForPatientRequest() {
      return this.securedGetPrescriptionForPatientRequest;
   }

   public void setSecuredGetPrescriptionForPatientRequest(SecuredContentType value) {
      this.securedGetPrescriptionForPatientRequest = value;
   }
}
