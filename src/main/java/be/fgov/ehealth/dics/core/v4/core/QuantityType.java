package be.fgov.ehealth.dics.core.v4.core;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QuantityType",
   propOrder = {"value"}
)
public class QuantityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected BigDecimal value;
   @XmlAttribute(
      name = "Unit",
      required = true
   )
   protected String unit;

   public BigDecimal getValue() {
      return this.value;
   }

   public void setValue(BigDecimal value) {
      this.value = value;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String value) {
      this.unit = value;
   }
}
