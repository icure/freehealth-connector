package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.DataLocationType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationHistoryEvent",
   propOrder = {"historyDate", "medicationHistoryEntity", "dataLocation"}
)
public class MedicationHistoryEvent extends AbstractEventType {
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar historyDate;
   @XmlElement(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1"
   )
   protected List<MedicationHistoryType> medicationHistoryEntity;
   protected List<DataLocationType> dataLocation;

   public XMLGregorianCalendar getHistoryDate() {
      return this.historyDate;
   }

   public void setHistoryDate(XMLGregorianCalendar value) {
      this.historyDate = value;
   }

   public List<MedicationHistoryType> getMedicationHistoryEntity() {
      if (this.medicationHistoryEntity == null) {
         this.medicationHistoryEntity = new ArrayList();
      }

      return this.medicationHistoryEntity;
   }

   public List<DataLocationType> getDataLocation() {
      if (this.dataLocation == null) {
         this.dataLocation = new ArrayList();
      }

      return this.dataLocation;
   }
}
