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
   name = "ListRidsHistoryResponseType",
   propOrder = {"securedListRidsHistoryResponse"}
)
@XmlRootElement(
   name = "ListRidsHistoryResponse"
)
public class ListRidsHistoryResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListRidsHistoryResponse"
   )
   protected SecuredContentType securedListRidsHistoryResponse;

   public SecuredContentType getSecuredListRidsHistoryResponse() {
      return this.securedListRidsHistoryResponse;
   }

   public void setSecuredListRidsHistoryResponse(SecuredContentType value) {
      this.securedListRidsHistoryResponse = value;
   }
}
