package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreateFeedbackRequestType",
   propOrder = {"securedCreateFeedbackRequest"}
)
@XmlRootElement(
   name = "CreateFeedbackRequest"
)
public class CreateFeedbackRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreateFeedbackRequest",
      required = true
   )
   protected SecuredContentType securedCreateFeedbackRequest;

   public SecuredContentType getSecuredCreateFeedbackRequest() {
      return this.securedCreateFeedbackRequest;
   }

   public void setSecuredCreateFeedbackRequest(SecuredContentType value) {
      this.securedCreateFeedbackRequest = value;
   }
}
