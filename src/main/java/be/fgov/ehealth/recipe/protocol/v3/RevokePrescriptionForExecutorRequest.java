package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v3.ExecutorServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePrescriptionForExecutorRequestType",
   propOrder = {"securedRevokePrescriptionRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "RevokePrescriptionForExecutorRequest"
)
public class RevokePrescriptionForExecutorRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredRevokePrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedRevokePrescriptionRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected ExecutorServiceAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredRevokePrescriptionRequest() {
      return this.securedRevokePrescriptionRequest;
   }

   public void setSecuredRevokePrescriptionRequest(SecuredContentType value) {
      this.securedRevokePrescriptionRequest = value;
   }

   public ExecutorServiceAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(ExecutorServiceAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
