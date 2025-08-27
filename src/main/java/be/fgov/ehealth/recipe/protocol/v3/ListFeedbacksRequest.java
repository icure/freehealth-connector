package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListFeedbacksRequestType",
   propOrder = {"securedListFeedbacksRequest"}
)
@XmlRootElement(
   name = "ListFeedbacksRequest"
)
public class ListFeedbacksRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredListFeedbacksRequest",
      required = true
   )
   protected SecuredContentType securedListFeedbacksRequest;

   public SecuredContentType getSecuredListFeedbacksRequest() {
      return this.securedListFeedbacksRequest;
   }

   public void setSecuredListFeedbacksRequest(SecuredContentType value) {
      this.securedListFeedbacksRequest = value;
   }
}
