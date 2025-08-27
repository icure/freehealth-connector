package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "sendNotificationResponse",
   propOrder = {"sendNotificationResultSealed"}
)
@XmlRootElement(
   name = "sendNotificationResponse"
)
public class SendNotificationResponse {
   @XmlElement(
      name = "SendNotificationResultSealed"
   )
   protected byte[] sendNotificationResultSealed;

   public byte[] getSendNotificationResultSealed() {
      return this.sendNotificationResultSealed;
   }

   public void setSendNotificationResultSealed(byte[] value) {
      this.sendNotificationResultSealed = value;
   }

}
