package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractEventType",
   propOrder = {"id"}
)
@XmlSeeAlso({ArchivePrescriptionEventType.class, ArchivePrescriptionCommentEventType.class, MedicationDeliveryEventType.class, RegisterExportEventType.class, PharmaceuticalCareEventType.class, BvacEventType.class, MedicationHistoryEvent.class, ContinuedPharmaceuticalCareDossierEvent.class, AbstractMedicationSchemeResponse.class, MedicationSchemeEntriesRequest.class})
public abstract class AbstractEventType {
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String id;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
