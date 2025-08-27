package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutFeedbackFlagRequestType",
   propOrder = {"securedPutFeedbackFlagRequest"}
)
@XmlRootElement(
   name = "PutFeedbackFlagRequest"
)
public class PutFeedbackFlagRequest extends RequestType {
   @XmlElement(
      name = "SecuredPutFeedbackFlagRequest",
      required = true
   )
   protected SecuredContentType securedPutFeedbackFlagRequest;

   public SecuredContentType getSecuredPutFeedbackFlagRequest() {
      return this.securedPutFeedbackFlagRequest;
   }

   public void setSecuredPutFeedbackFlagRequest(SecuredContentType value) {
      this.securedPutFeedbackFlagRequest = value;
   }
}
