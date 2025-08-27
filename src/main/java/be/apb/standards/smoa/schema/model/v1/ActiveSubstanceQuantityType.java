package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActiveSubstanceQuantityType",
   propOrder = {"quantity", "unit"}
)
public class ActiveSubstanceQuantityType {
   protected int quantity;
   @XmlElement(
      required = true
   )
   protected String unit;

   public int getQuantity() {
      return this.quantity;
   }

   public void setQuantity(int value) {
      this.quantity = value;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String value) {
      this.unit = value;
   }
}
