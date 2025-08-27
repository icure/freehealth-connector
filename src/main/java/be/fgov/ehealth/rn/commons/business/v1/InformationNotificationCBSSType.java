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
   name = "InformationNotificationCBSSType",
   propOrder = {"ticketCBSS", "timestampSent"}
)
public class InformationNotificationCBSSType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TicketCBSS",
      required = true
   )
   protected String ticketCBSS;
   @XmlElement(
      name = "TimestampSent",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampSent;

   public String getTicketCBSS() {
      return this.ticketCBSS;
   }

   public void setTicketCBSS(String value) {
      this.ticketCBSS = value;
   }

   public DateTime getTimestampSent() {
      return this.timestampSent;
   }

   public void setTimestampSent(DateTime value) {
      this.timestampSent = value;
   }
}
