package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutVisionForPatientRequestType",
   propOrder = {"securedPutVisionForPatientRequest"}
)
@XmlRootElement(
   name = "PutVisionForPatientRequest"
)
public class PutVisionForPatientRequest extends RequestType {
   @XmlElement(
      name = "SecuredPutVisionForPatientRequest",
      required = true
   )
   protected SecuredContentType securedPutVisionForPatientRequest;

   public SecuredContentType getSecuredPutVisionForPatientRequest() {
      return this.securedPutVisionForPatientRequest;
   }

   public void setSecuredPutVisionForPatientRequest(SecuredContentType value) {
      this.securedPutVisionForPatientRequest = value;
   }
}
