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
   name = "InformationCBSSType",
   propOrder = {"ticketCBSS", "timestampReceive", "timestampReply"}
)
public class InformationCBSSType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TicketCBSS",
      required = true
   )
   protected String ticketCBSS;
   @XmlElement(
      name = "TimestampReceive",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampReceive;
   @XmlElement(
      name = "TimestampReply",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestampReply;

   public String getTicketCBSS() {
      return this.ticketCBSS;
   }

   public void setTicketCBSS(String value) {
      this.ticketCBSS = value;
   }

   public DateTime getTimestampReceive() {
      return this.timestampReceive;
   }

   public void setTimestampReceive(DateTime value) {
      this.timestampReceive = value;
   }

   public DateTime getTimestampReply() {
      return this.timestampReply;
   }

   public void setTimestampReply(DateTime value) {
      this.timestampReply = value;
   }
}
