package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectGetTransactionListType",
   propOrder = {"patient", "transaction", "searchtype"}
)
public class SelectGetTransactionListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   protected BasicTransactionWithPeriodType transaction;
   @XmlSchemaType(
      name = "string"
   )
   protected LocalSearchType searchtype;

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public BasicTransactionWithPeriodType getTransaction() {
      return this.transaction;
   }

   public void setTransaction(BasicTransactionWithPeriodType value) {
      this.transaction = value;
   }

   public LocalSearchType getSearchtype() {
      return this.searchtype;
   }

   public void setSearchtype(LocalSearchType value) {
      this.searchtype = value;
   }
}
