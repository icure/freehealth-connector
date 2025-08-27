package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProductList",
   propOrder = {"product"}
)
public class ProductList {
   @XmlElement(
      required = true
   )
   protected List<ProductBvac> product;

   public List<ProductBvac> getProduct() {
      if (this.product == null) {
         this.product = new ArrayList();
      }

      return this.product;
   }
}
