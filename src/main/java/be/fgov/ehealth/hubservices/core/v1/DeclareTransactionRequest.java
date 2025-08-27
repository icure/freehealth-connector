package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeclareTransactionRequestType",
   propOrder = {"request", "kmehrheader"}
)
@XmlRootElement(
   name = "DeclareTransactionRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v1"
)
public class DeclareTransactionRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected KmehrHeaderDeclareTransaction kmehrheader;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public KmehrHeaderDeclareTransaction getKmehrheader() {
      return this.kmehrheader;
   }

   public void setKmehrheader(KmehrHeaderDeclareTransaction value) {
      this.kmehrheader = value;
   }
}
