package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MomentType",
   propOrder = {"year", "yearmonth", "date", "time", "text"}
)
public class MomentType {
   @XmlSchemaType(
      name = "gYear"
   )
   protected XMLGregorianCalendar year;
   @XmlSchemaType(
      name = "gYearMonth"
   )
   protected XMLGregorianCalendar yearmonth;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar date;
   @XmlSchemaType(
      name = "time"
   )
   protected XMLGregorianCalendar time;
   protected String text;

   public XMLGregorianCalendar getYear() {
      return this.year;
   }

   public void setYear(XMLGregorianCalendar value) {
      this.year = value;
   }

   public XMLGregorianCalendar getYearmonth() {
      return this.yearmonth;
   }

   public void setYearmonth(XMLGregorianCalendar value) {
      this.yearmonth = value;
   }

   public XMLGregorianCalendar getDate() {
      return this.date;
   }

   public void setDate(XMLGregorianCalendar value) {
      this.date = value;
   }

   public XMLGregorianCalendar getTime() {
      return this.time;
   }

   public void setTime(XMLGregorianCalendar value) {
      this.time = value;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String value) {
      this.text = value;
   }
}
