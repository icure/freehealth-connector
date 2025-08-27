package be.apb.gfddpp.productfilter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "medicine",
   propOrder = {"productId", "category"}
)
public class Medicine {
   @XmlElement(
      name = "product-id"
   )
   protected String productId;
   protected String category;

   public String getProductId() {
      return this.productId;
   }

   public void setProductId(String value) {
      this.productId = value;
   }

   public String getCategory() {
      return this.category;
   }

   public void setCategory(String value) {
      this.category = value;
   }
}
