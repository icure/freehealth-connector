package be.fgov.ehealth.recipe.core.v3;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExecutorServiceAdministrativeInformationType",
   propOrder = {"prescriberIdentifier", "patientIdentifier"}
)
public class ExecutorServiceAdministrativeInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PrescriberIdentifier",
      required = true
   )
   protected IdentifierType prescriberIdentifier;
   @XmlElement(
      name = "PatientIdentifier",
      required = true
   )
   protected IdentifierType patientIdentifier;

   public IdentifierType getPrescriberIdentifier() {
      return this.prescriberIdentifier;
   }

   public void setPrescriberIdentifier(IdentifierType value) {
      this.prescriberIdentifier = value;
   }

   public IdentifierType getPatientIdentifier() {
      return this.patientIdentifier;
   }

   public void setPatientIdentifier(IdentifierType value) {
      this.patientIdentifier = value;
   }
}
