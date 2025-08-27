package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendNotificationRequestType",
   propOrder = {"securedSendNotificationRequest"}
)
@XmlRootElement(
   name = "SendNotificationRequest"
)
public class SendNotificationRequest extends RequestType {
   @XmlElement(
      name = "SecuredSendNotificationRequest",
      required = true
   )
   protected SecuredContentType securedSendNotificationRequest;

   public SecuredContentType getSecuredSendNotificationRequest() {
      return this.securedSendNotificationRequest;
   }

   public void setSecuredSendNotificationRequest(SecuredContentType value) {
      this.securedSendNotificationRequest = value;
   }
}
