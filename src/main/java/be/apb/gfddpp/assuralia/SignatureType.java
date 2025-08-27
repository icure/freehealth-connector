package be.apb.gfddpp.assuralia;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignatureType",
   propOrder = {"dateTime", "signature"}
)
public class SignatureType {
   @XmlElement(
      name = "DateTime",
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar dateTime;
   @XmlElement(
      name = "Signature",
      required = true
   )
   protected String signature;

   public XMLGregorianCalendar getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(XMLGregorianCalendar value) {
      this.dateTime = value;
   }

   public String getSignature() {
      return this.signature;
   }

   public void setSignature(String value) {
      this.signature = value;
   }
}
