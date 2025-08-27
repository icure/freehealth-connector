package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PaymentType",
   propOrder = {"paymentByIo", "maxInvoiceds", "specialSocialCategory"}
)
@XmlRootElement(
   name = "Payment"
)
public class Payment implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PaymentByIo"
   )
   protected boolean paymentByIo;
   @XmlElement(
      name = "MaxInvoiced"
   )
   @XmlSchemaType(
      name = "gYear"
   )
   protected List<XMLGregorianCalendar> maxInvoiceds;
   @XmlElement(
      name = "SpecialSocialCategory"
   )
   protected Boolean specialSocialCategory;

   public boolean isPaymentByIo() {
      return this.paymentByIo;
   }

   public void setPaymentByIo(boolean value) {
      this.paymentByIo = value;
   }

   public List<XMLGregorianCalendar> getMaxInvoiceds() {
      if (this.maxInvoiceds == null) {
         this.maxInvoiceds = new ArrayList();
      }

      return this.maxInvoiceds;
   }

   public Boolean isSpecialSocialCategory() {
      return this.specialSocialCategory;
   }

   public void setSpecialSocialCategory(Boolean value) {
      this.specialSocialCategory = value;
   }
}
