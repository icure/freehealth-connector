package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AmountsBvacProduct",
   propOrder = {"publicPrice", "paidPrice"}
)
public class AmountsBvacProduct {
   @XmlElement(
      required = true
   )
   protected String publicPrice;
   @XmlElement(
      required = true
   )
   protected String paidPrice;

   public String getPublicPrice() {
      return this.publicPrice;
   }

   public void setPublicPrice(String value) {
      this.publicPrice = value;
   }

   public String getPaidPrice() {
      return this.paidPrice;
   }

   public void setPaidPrice(String value) {
      this.paidPrice = value;
   }
}
