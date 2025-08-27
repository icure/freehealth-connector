package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsDeliveredRequestType",
   propOrder = {"securedMarkAsDeliveredRequest"}
)
@XmlRootElement(
   name = "MarkAsDeliveredRequest"
)
public class MarkAsDeliveredRequest extends RequestType {
   @XmlElement(
      name = "SecuredMarkAsDeliveredRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsDeliveredRequest;

   public SecuredContentType getSecuredMarkAsDeliveredRequest() {
      return this.securedMarkAsDeliveredRequest;
   }

   public void setSecuredMarkAsDeliveredRequest(SecuredContentType value) {
      this.securedMarkAsDeliveredRequest = value;
   }
}
