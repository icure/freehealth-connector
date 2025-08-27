package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationSchemeEntriesResponse",
   propOrder = {"subjectId", "medicationScheme"}
)
public class MedicationSchemeEntriesResponse extends AbstractMedicationSchemeResponse {
   protected String subjectId;
   protected MedicationSchemeResponse medicationScheme;

   public String getSubjectId() {
      return this.subjectId;
   }

   public void setSubjectId(String value) {
      this.subjectId = value;
   }

   public MedicationSchemeResponse getMedicationScheme() {
      return this.medicationScheme;
   }

   public void setMedicationScheme(MedicationSchemeResponse value) {
      this.medicationScheme = value;
   }
}
