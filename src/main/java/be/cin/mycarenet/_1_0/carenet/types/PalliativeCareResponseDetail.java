package be.cin.mycarenet._1_0.carenet.types;

import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PalliativeCareResponseDetailType",
   propOrder = {"consultantDoctor", "decision", "invoiceStartDate"}
)
@XmlRootElement(
   name = "PalliativeCareResponseDetail"
)
public class PalliativeCareResponseDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ConsultantDoctor"
   )
   protected String consultantDoctor;
   @XmlElement(
      name = "Decision",
      required = true
   )
   protected DecisionType decision;
   @XmlElement(
      name = "InvoiceStartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime invoiceStartDate;

   public String getConsultantDoctor() {
      return this.consultantDoctor;
   }

   public void setConsultantDoctor(String value) {
      this.consultantDoctor = value;
   }

   public DecisionType getDecision() {
      return this.decision;
   }

   public void setDecision(DecisionType value) {
      this.decision = value;
   }

   public DateTime getInvoiceStartDate() {
      return this.invoiceStartDate;
   }

   public void setInvoiceStartDate(DateTime value) {
      this.invoiceStartDate = value;
   }
}
