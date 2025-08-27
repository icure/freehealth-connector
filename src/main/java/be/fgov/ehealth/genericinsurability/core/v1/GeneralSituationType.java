package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GeneralSituationType",
   propOrder = {"transfers"}
)
public class GeneralSituationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Transfer"
   )
   protected List<TransferType> transfers;
   @XmlAttribute(
      name = "Event",
      required = true
   )
   protected EventType event;

   public List<TransferType> getTransfers() {
      if (this.transfers == null) {
         this.transfers = new ArrayList();
      }

      return this.transfers;
   }

   public EventType getEvent() {
      return this.event;
   }

   public void setEvent(EventType value) {
      this.event = value;
   }
}
