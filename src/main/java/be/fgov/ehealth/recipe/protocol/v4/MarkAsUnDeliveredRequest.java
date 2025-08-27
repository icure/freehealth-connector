package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsUnDeliveredRequestType",
   propOrder = {"securedMarkAsUnDeliveredRequest"}
)
@XmlRootElement(
   name = "MarkAsUnDeliveredRequest"
)
public class MarkAsUnDeliveredRequest extends RequestType {
   @XmlElement(
      name = "SecuredMarkAsUnDeliveredRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsUnDeliveredRequest;

   public SecuredContentType getSecuredMarkAsUnDeliveredRequest() {
      return this.securedMarkAsUnDeliveredRequest;
   }

   public void setSecuredMarkAsUnDeliveredRequest(SecuredContentType value) {
      this.securedMarkAsUnDeliveredRequest = value;
   }
}
