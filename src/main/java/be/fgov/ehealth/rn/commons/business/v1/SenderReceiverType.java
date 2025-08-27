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
   name = "SenderReceiverType",
   propOrder = {"ticket", "timestampSent", "organizationIdentification"}
)
public class SenderReceiverType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ticket"
   )
   protected String ticket;
   @XmlElement(
      name = "TimestampSent",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampSent;
   @XmlElement(
      name = "OrganizationIdentification",
      required = true
   )
   protected OrganizationIdentificationType organizationIdentification;

   public String getTicket() {
      return this.ticket;
   }

   public void setTicket(String value) {
      this.ticket = value;
   }

   public DateTime getTimestampSent() {
      return this.timestampSent;
   }

   public void setTimestampSent(DateTime value) {
      this.timestampSent = value;
   }

   public OrganizationIdentificationType getOrganizationIdentification() {
      return this.organizationIdentification;
   }

   public void setOrganizationIdentification(OrganizationIdentificationType value) {
      this.organizationIdentification = value;
   }
}
