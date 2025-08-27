package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   name = "AmppListDataType",
   propOrder = {"atcCodes", "abbreviatedName", "prescriptionName"}
)
public class AmppListDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AtcCode"
   )
   protected List<String> atcCodes;
   @XmlElement(
      name = "AbbreviatedName"
   )
   protected ConsultTextType abbreviatedName;
   @XmlElement(
      name = "PrescriptionName"
   )
   protected ConsultTextType prescriptionName;
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

   public List<String> getAtcCodes() {
      if (this.atcCodes == null) {
         this.atcCodes = new ArrayList();
      }

      return this.atcCodes;
   }

   public ConsultTextType getAbbreviatedName() {
      return this.abbreviatedName;
   }

   public void setAbbreviatedName(ConsultTextType value) {
      this.abbreviatedName = value;
   }

   public ConsultTextType getPrescriptionName() {
      return this.prescriptionName;
   }

   public void setPrescriptionName(ConsultTextType value) {
      this.prescriptionName = value;
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
