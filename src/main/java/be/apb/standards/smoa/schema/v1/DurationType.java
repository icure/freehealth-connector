package be.apb.standards.smoa.schema.v1;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DurationType",
   propOrder = {"decimal", "unit"}
)
public class DurationType {
   @XmlElement(
      required = true
   )
   protected BigDecimal decimal;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected CDTIMEUNIT unit;

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public CDTIMEUNIT getUnit() {
      return this.unit;
   }

   public void setUnit(CDTIMEUNIT value) {
      this.unit = value;
   }
}
