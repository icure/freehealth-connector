package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractAdministrationUnitValuesCodeType;
import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegimenAdministrationQuantityType",
   propOrder = {"decimal", "unit"}
)
public class RegimenAdministrationQuantityType {
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   protected AbstractAdministrationUnitValuesCodeType unit;

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public AbstractAdministrationUnitValuesCodeType getUnit() {
      return this.unit;
   }

   public void setUnit(AbstractAdministrationUnitValuesCodeType value) {
      this.unit = value;
   }
}
