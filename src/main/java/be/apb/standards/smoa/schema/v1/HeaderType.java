package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HeaderType",
   propOrder = {"version", "messageCreateDate", "messageID", "sender"}
)
public class HeaderType {
   @XmlElement(
      required = true
   )
   protected String version;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar messageCreateDate;
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String messageID;
   @XmlElement(
      required = true
   )
   protected SenderType sender;

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public XMLGregorianCalendar getMessageCreateDate() {
      return this.messageCreateDate;
   }

   public void setMessageCreateDate(XMLGregorianCalendar value) {
      this.messageCreateDate = value;
   }

   public String getMessageID() {
      return this.messageID;
   }

   public void setMessageID(String value) {
      this.messageID = value;
   }

   public SenderType getSender() {
      return this.sender;
   }

   public void setSender(SenderType value) {
      this.sender = value;
   }
}
