package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.PrescriberPutVisionInformationType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutVisionForPrescriberRequestType",
   propOrder = {"securedPutVisionForPrescriberRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "PutVisionForPrescriberRequest"
)
public class PutVisionForPrescriberRequest extends RequestType {
   @XmlElement(
      name = "SecuredPutVisionForPrescriberRequest",
      required = true
   )
   protected SecuredContentType securedPutVisionForPrescriberRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected PrescriberPutVisionInformationType administrativeInformation;

   public SecuredContentType getSecuredPutVisionForPrescriberRequest() {
      return this.securedPutVisionForPrescriberRequest;
   }

   public void setSecuredPutVisionForPrescriberRequest(SecuredContentType value) {
      this.securedPutVisionForPrescriberRequest = value;
   }

   public PrescriberPutVisionInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(PrescriberPutVisionInformationType value) {
      this.administrativeInformation = value;
   }
}
