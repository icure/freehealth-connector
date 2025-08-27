package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePatientConsentRequestType",
   propOrder = {"request", "consent"}
)
@XmlRootElement(
   name = "RevokePatientConsentRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class RevokePatientConsentRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected ConsentType consent;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public ConsentType getConsent() {
      return this.consent;
   }

   public void setConsent(ConsentType value) {
      this.consent = value;
   }
}
