package be.fgov.ehealth.timestamping.protocol.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TimeStampIdentification",
   propOrder = {"sequenceNumber", "dateTime"}
)
public class TimeStampIdentification implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected String sequenceNumber;
   protected long dateTime;

   public String getSequenceNumber() {
      return this.sequenceNumber;
   }

   public void setSequenceNumber(String value) {
      this.sequenceNumber = value;
   }

   public long getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(long value) {
      this.dateTime = value;
   }
}
