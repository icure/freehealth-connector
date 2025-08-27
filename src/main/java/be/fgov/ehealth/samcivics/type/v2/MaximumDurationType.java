package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MaximumDurationType",
   propOrder = {"value"}
)
public class MaximumDurationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected float value;
   @XmlAttribute(
      name = "unit",
      required = true
   )
   protected ValidityPeriodUnit unit;

   public float getValue() {
      return this.value;
   }

   public void setValue(float value) {
      this.value = value;
   }

   public ValidityPeriodUnit getUnit() {
      return this.unit;
   }

   public void setUnit(ValidityPeriodUnit value) {
      this.unit = value;
   }
}
