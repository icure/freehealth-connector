package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TransactionAccessListType",
   propOrder = {"transactionaccesses"}
)
@XmlRootElement(
   name = "TransactionAccessListType"
)
public class TransactionAccessListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "transactionaccess"
   )
   protected List<Transactionaccess> transactionaccesses;

   public List<Transactionaccess> getTransactionaccesses() {
      if (this.transactionaccesses == null) {
         this.transactionaccesses = new ArrayList();
      }

      return this.transactionaccesses;
   }
}
