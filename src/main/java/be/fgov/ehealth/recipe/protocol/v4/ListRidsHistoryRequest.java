package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListRidsHistoryRequestType",
   propOrder = {"securedListRidsHistoryRequest"}
)
@XmlRootElement(
   name = "ListRidsHistoryRequest"
)
public class ListRidsHistoryRequest extends RequestType {
   @XmlElement(
      name = "SecuredListRidsHistoryRequest",
      required = true
   )
   protected SecuredContentType securedListRidsHistoryRequest;

   public SecuredContentType getSecuredListRidsHistoryRequest() {
      return this.securedListRidsHistoryRequest;
   }

   public void setSecuredListRidsHistoryRequest(SecuredContentType value) {
      this.securedListRidsHistoryRequest = value;
   }
}
