package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "sendNotification",
   propOrder = {"sendNotificationParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "sendNotification"
)
public class SendNotification implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SendNotificationParamSealed"
   )
   protected byte[] sendNotificationParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getSendNotificationParamSealed() {
      return this.sendNotificationParamSealed;
   }

   public void setSendNotificationParamSealed(byte[] value) {
      this.sendNotificationParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
