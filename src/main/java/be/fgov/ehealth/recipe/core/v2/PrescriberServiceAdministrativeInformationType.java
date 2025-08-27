package be.fgov.ehealth.recipe.core.v2;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PrescriberServiceAdministrativeInformationType",
   propOrder = {"patientIdentifier"}
)
@XmlSeeAlso({CreatePrescriptionAdministrativeInformationType.class, SendNotificationAdministrativeInformationType.class})
public class PrescriberServiceAdministrativeInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PatientIdentifier",
      required = true
   )
   protected IdentifierType patientIdentifier;

   public IdentifierType getPatientIdentifier() {
      return this.patientIdentifier;
   }

   public void setPatientIdentifier(IdentifierType value) {
      this.patientIdentifier = value;
   }
}
