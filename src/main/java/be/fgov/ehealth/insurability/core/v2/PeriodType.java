package be.fgov.ehealth.insurability.core.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PeriodType",
   propOrder = {"beginDate", "endDate"}
)
public class PeriodType {
   @XmlElement(
      name = "BeginDate",
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar beginDate;
   @XmlElement(
      name = "EndDate",
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar endDate;

   public XMLGregorianCalendar getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(XMLGregorianCalendar value) {
      this.beginDate = value;
   }

   public XMLGregorianCalendar getEndDate() {
      return this.endDate;
   }

   public void setEndDate(XMLGregorianCalendar value) {
      this.endDate = value;
   }
}
