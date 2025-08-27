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
   name = "ListFeedbacksResponseType",
   propOrder = {"securedListFeedbacksResponse"}
)
@XmlRootElement(
   name = "ListFeedbacksResponse"
)
public class ListFeedbacksResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListFeedbacksResponse"
   )
   protected SecuredContentType securedListFeedbacksResponse;

   public SecuredContentType getSecuredListFeedbacksResponse() {
      return this.securedListFeedbacksResponse;
   }

   public void setSecuredListFeedbacksResponse(SecuredContentType value) {
      this.securedListFeedbacksResponse = value;
   }
}
