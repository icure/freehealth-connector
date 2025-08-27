package be.fgov.ehealth.insurability.core.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsurabilityForPharmacistRequestType",
   propOrder = {"date", "requestType", "careReceiver"}
)
public class InsurabilityForPharmacistRequestType {
   @XmlElement(
      name = "Date",
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar date;
   @XmlElement(
      name = "RequestType",
      required = true
   )
   protected String requestType;
   @XmlElement(
      name = "CareReceiver",
      required = true
   )
   protected CareReceiverIdType careReceiver;

   public XMLGregorianCalendar getDate() {
      return this.date;
   }

   public void setDate(XMLGregorianCalendar value) {
      this.date = value;
   }

   public String getRequestType() {
      return this.requestType;
   }

   public void setRequestType(String value) {
      this.requestType = value;
   }

   public CareReceiverIdType getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverIdType value) {
      this.careReceiver = value;
   }
}
