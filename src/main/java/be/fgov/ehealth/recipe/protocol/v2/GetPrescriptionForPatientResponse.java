package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForPatientResponseType",
   propOrder = {"securedGetPrescriptionForPatientResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionForPatientResponse"
)
public class GetPrescriptionForPatientResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredGetPrescriptionForPatientResponse"
   )
   protected SecuredContentType securedGetPrescriptionForPatientResponse;

   public SecuredContentType getSecuredGetPrescriptionForPatientResponse() {
      return this.securedGetPrescriptionForPatientResponse;
   }

   public void setSecuredGetPrescriptionForPatientResponse(SecuredContentType value) {
      this.securedGetPrescriptionForPatientResponse = value;
   }
}
