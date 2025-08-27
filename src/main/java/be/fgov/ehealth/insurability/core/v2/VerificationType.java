package be.fgov.ehealth.insurability.core.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VerificationType",
   propOrder = {"paymentApproval", "paymentApprovalSeed", "invoicingOfficeCheckDigit"}
)
public class VerificationType {
   @XmlElement(
      name = "PaymentApproval"
   )
   protected String paymentApproval;
   @XmlElement(
      name = "PaymentApprovalSeed"
   )
   protected String paymentApprovalSeed;
   @XmlElement(
      name = "InvoicingOfficeCheckDigit",
      required = true
   )
   protected String invoicingOfficeCheckDigit;

   public String getPaymentApproval() {
      return this.paymentApproval;
   }

   public void setPaymentApproval(String value) {
      this.paymentApproval = value;
   }

   public String getPaymentApprovalSeed() {
      return this.paymentApprovalSeed;
   }

   public void setPaymentApprovalSeed(String value) {
      this.paymentApprovalSeed = value;
   }

   public String getInvoicingOfficeCheckDigit() {
      return this.invoicingOfficeCheckDigit;
   }

   public void setInvoicingOfficeCheckDigit(String value) {
      this.invoicingOfficeCheckDigit = value;
   }
}
