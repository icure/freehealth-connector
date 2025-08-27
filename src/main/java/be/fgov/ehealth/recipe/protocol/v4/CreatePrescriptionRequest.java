package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePrescriptionRequestType",
   propOrder = {"securedCreatePrescriptionRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "CreatePrescriptionRequest"
)
public class CreatePrescriptionRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreatePrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedCreatePrescriptionRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected CreatePrescriptionAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredCreatePrescriptionRequest() {
      return this.securedCreatePrescriptionRequest;
   }

   public void setSecuredCreatePrescriptionRequest(SecuredContentType value) {
      this.securedCreatePrescriptionRequest = value;
   }

   public CreatePrescriptionAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(CreatePrescriptionAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
