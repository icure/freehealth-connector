package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentificationBvacProduct",
   propOrder = {"productTypeId", "id"}
)
public class IdentificationBvacProduct {
   @XmlElement(
      required = true
   )
   protected String productTypeId;
   @XmlElement(
      required = true
   )
   protected String id;

   public String getProductTypeId() {
      return this.productTypeId;
   }

   public void setProductTypeId(String value) {
      this.productTypeId = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
