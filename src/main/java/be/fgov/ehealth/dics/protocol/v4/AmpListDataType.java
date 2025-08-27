package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AmpListDataType",
   propOrder = {"officialName", "name", "prescriptionName", "abbreviatedName"}
)
public class AmpListDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OfficialName"
   )
   protected String officialName;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "PrescriptionName"
   )
   protected ConsultTextType prescriptionName;
   @XmlElement(
      name = "AbbreviatedName"
   )
   protected ConsultTextType abbreviatedName;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public String getOfficialName() {
      return this.officialName;
   }

   public void setOfficialName(String value) {
      this.officialName = value;
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public ConsultTextType getPrescriptionName() {
      return this.prescriptionName;
   }

   public void setPrescriptionName(ConsultTextType value) {
      this.prescriptionName = value;
   }

   public ConsultTextType getAbbreviatedName() {
      return this.abbreviatedName;
   }

   public void setAbbreviatedName(ConsultTextType value) {
      this.abbreviatedName = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
