package be.fgov.ehealth.recipe.core.v4;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PrescriberPutVisionInformationType",
   propOrder = {"prescriptionVersion", "referenceSourceVersion"}
)
public class PrescriberPutVisionInformationType {
   @XmlElement(
      name = "PrescriptionVersion",
      required = true
   )
   protected String prescriptionVersion;
   @XmlElement(
      name = "ReferenceSourceVersion",
      required = true
   )
   protected String referenceSourceVersion;

   public String getPrescriptionVersion() {
      return this.prescriptionVersion;
   }

   public void setPrescriptionVersion(String value) {
      this.prescriptionVersion = value;
   }

   public String getReferenceSourceVersion() {
      return this.referenceSourceVersion;
   }

   public void setReferenceSourceVersion(String value) {
      this.referenceSourceVersion = value;
   }
}
