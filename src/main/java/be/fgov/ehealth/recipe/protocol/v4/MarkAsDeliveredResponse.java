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
   name = "MarkAsDeliveredResponseType",
   propOrder = {"securedMarkAsDeliveredResponse"}
)
@XmlRootElement(
   name = "MarkAsDeliveredResponse"
)
public class MarkAsDeliveredResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredMarkAsDeliveredResponse"
   )
   protected SecuredContentType securedMarkAsDeliveredResponse;

   public SecuredContentType getSecuredMarkAsDeliveredResponse() {
      return this.securedMarkAsDeliveredResponse;
   }

   public void setSecuredMarkAsDeliveredResponse(SecuredContentType value) {
      this.securedMarkAsDeliveredResponse = value;
   }
}
