package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListNotificationsRequestType",
   propOrder = {"securedListNotificationsRequest"}
)
@XmlRootElement(
   name = "ListNotificationsRequest"
)
public class ListNotificationsRequest extends RequestType {
   @XmlElement(
      name = "SecuredListNotificationsRequest",
      required = true
   )
   protected SecuredContentType securedListNotificationsRequest;

   public SecuredContentType getSecuredListNotificationsRequest() {
      return this.securedListNotificationsRequest;
   }

   public void setSecuredListNotificationsRequest(SecuredContentType value) {
      this.securedListNotificationsRequest = value;
   }
}
