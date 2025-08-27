package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPatientAuditTrailResponseType",
   propOrder = {"response", "acknowledge", "transactionaccesslist"}
)
@XmlRootElement(
   name = "GetPatientAuditTrailResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class GetPatientAuditTrailResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseListType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   protected TransactionAccessListType transactionaccesslist;

   public ResponseListType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseListType value) {
      this.response = value;
   }

   public AcknowledgeType getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(AcknowledgeType value) {
      this.acknowledge = value;
   }

   public TransactionAccessListType getTransactionaccesslist() {
      return this.transactionaccesslist;
   }

   public void setTransactionaccesslist(TransactionAccessListType value) {
      this.transactionaccesslist = value;
   }
}
