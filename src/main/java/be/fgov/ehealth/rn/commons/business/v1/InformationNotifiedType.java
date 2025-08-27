package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InformationNotifiedType",
   propOrder = {"ticket", "timestampReply", "notifiedIdentification"}
)
public class InformationNotifiedType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ticket"
   )
   protected String ticket;
   @XmlElement(
      name = "TimestampReply",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampReply;
   @XmlElement(
      name = "NotifiedIdentification",
      required = true
   )
   protected OrganizationIdentificationType notifiedIdentification;

   public String getTicket() {
      return this.ticket;
   }

   public void setTicket(String value) {
      this.ticket = value;
   }

   public DateTime getTimestampReply() {
      return this.timestampReply;
   }

   public void setTimestampReply(DateTime value) {
      this.timestampReply = value;
   }

   public OrganizationIdentificationType getNotifiedIdentification() {
      return this.notifiedIdentification;
   }

   public void setNotifiedIdentification(OrganizationIdentificationType value) {
      this.notifiedIdentification = value;
   }
}
