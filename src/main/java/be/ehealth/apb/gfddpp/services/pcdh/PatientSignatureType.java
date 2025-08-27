package be.ehealth.apb.gfddpp.services.pcdh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PatientSignatureType",
   namespace = "urn:be:fgov:ehealth:gfddpp:core:v1",
   propOrder = {"dateTime", "signature"}
)
public class PatientSignatureType {
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
   protected byte[] signature;

   public XMLGregorianCalendar getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(XMLGregorianCalendar var1) {
      this.dateTime = var1;
   }

   public byte[] getSignature() {
      return this.signature;
   }

   public void setSignature(byte[] var1) {
      this.signature = (byte[])var1;
   }
}
