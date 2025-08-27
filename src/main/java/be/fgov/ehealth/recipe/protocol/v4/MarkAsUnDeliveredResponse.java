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
   name = "MarkAsUnDeliveredResponseType",
   propOrder = {"securedMarkAsUnDeliveredResponse"}
)
@XmlRootElement(
   name = "MarkAsUnDeliveredResponse"
)
public class MarkAsUnDeliveredResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredMarkAsUnDeliveredResponse"
   )
   protected SecuredContentType securedMarkAsUnDeliveredResponse;

   public SecuredContentType getSecuredMarkAsUnDeliveredResponse() {
      return this.securedMarkAsUnDeliveredResponse;
   }

   public void setSecuredMarkAsUnDeliveredResponse(SecuredContentType value) {
      this.securedMarkAsUnDeliveredResponse = value;
   }
}
