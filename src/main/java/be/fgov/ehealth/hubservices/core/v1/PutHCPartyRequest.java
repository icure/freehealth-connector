package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutHCPartyRequestType",
   propOrder = {"request", "hcparty"}
)
@XmlRootElement(
   name = "PutHCPartyRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class PutHCPartyRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected HCPartyAdaptedType hcparty;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public HCPartyAdaptedType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HCPartyAdaptedType value) {
      this.hcparty = value;
   }
}
