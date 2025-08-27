package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsArchivedRequestType",
   propOrder = {"securedMarkAsArchivedRequest"}
)
@XmlRootElement(
   name = "MarkAsArchivedRequest"
)
public class MarkAsArchivedRequest extends RequestType {
   @XmlElement(
      name = "SecuredMarkAsArchivedRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsArchivedRequest;

   public SecuredContentType getSecuredMarkAsArchivedRequest() {
      return this.securedMarkAsArchivedRequest;
   }

   public void setSecuredMarkAsArchivedRequest(SecuredContentType value) {
      this.securedMarkAsArchivedRequest = value;
   }
}
