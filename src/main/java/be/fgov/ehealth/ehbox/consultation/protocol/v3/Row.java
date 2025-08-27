package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.ehbox.core.v3.EhboxIdentifierType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"recipient", "published", "received", "read"}
)
public class Row implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Recipient",
      required = true
   )
   protected EhboxIdentifierType recipient;
   @XmlElement(
      name = "Published",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime published;
   @XmlElement(
      name = "Received",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime received;
   @XmlElement(
      name = "Read",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime read;

   public EhboxIdentifierType getRecipient() {
      return this.recipient;
   }

   public void setRecipient(EhboxIdentifierType value) {
      this.recipient = value;
   }

   public DateTime getPublished() {
      return this.published;
   }

   public void setPublished(DateTime value) {
      this.published = value;
   }

   public DateTime getReceived() {
      return this.received;
   }

   public void setReceived(DateTime value) {
      this.received = value;
   }

   public DateTime getRead() {
      return this.read;
   }

   public void setRead(DateTime value) {
      this.read = value;
   }
}
