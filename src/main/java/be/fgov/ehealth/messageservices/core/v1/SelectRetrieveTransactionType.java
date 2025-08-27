package be.fgov.ehealth.messageservices.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectRetrieveTransactionType",
   propOrder = {"patient", "transaction"}
)
public class SelectRetrieveTransactionType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected PatientType patient;
   @XmlElement(
      required = true
   )
   protected TransactionType transaction;

   public PatientType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientType value) {
      this.patient = value;
   }

   public TransactionType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(TransactionType value) {
      this.transaction = value;
   }
}
