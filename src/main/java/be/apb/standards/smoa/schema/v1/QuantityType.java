package be.apb.standards.smoa.schema.v1;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QuantityType",
   propOrder = {"decimal", "unit"}
)
public class QuantityType {
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   protected String unit;

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String value) {
      this.unit = value;
   }
}
