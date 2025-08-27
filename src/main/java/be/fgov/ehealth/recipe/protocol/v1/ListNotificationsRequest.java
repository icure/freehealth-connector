package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
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
public class ListNotificationsRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
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
