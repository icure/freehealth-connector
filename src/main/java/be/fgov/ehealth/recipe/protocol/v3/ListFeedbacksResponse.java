package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
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
public class ListFeedbacksResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
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
