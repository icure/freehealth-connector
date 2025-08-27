package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendNotificationResponseType",
   propOrder = {"securedSendNotificationResponse"}
)
@XmlRootElement(
   name = "SendNotificationResponse"
)
public class SendNotificationResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredSendNotificationResponse"
   )
   protected SecuredContentType securedSendNotificationResponse;

   public SecuredContentType getSecuredSendNotificationResponse() {
      return this.securedSendNotificationResponse;
   }

   public void setSecuredSendNotificationResponse(SecuredContentType value) {
      this.securedSendNotificationResponse = value;
   }
}
