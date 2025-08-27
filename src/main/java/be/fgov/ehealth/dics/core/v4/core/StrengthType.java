package be.fgov.ehealth.dics.core.v4.core;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StrengthType",
   propOrder = {"numerator", "denominator"}
)
public class StrengthType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Numerator",
      required = true
   )
   protected QuantityType numerator;
   @XmlElement(
      name = "Denominator",
      required = true
   )
   protected QuantityType denominator;

   public QuantityType getNumerator() {
      return this.numerator;
   }

   public void setNumerator(QuantityType value) {
      this.numerator = value;
   }

   public QuantityType getDenominator() {
      return this.denominator;
   }

   public void setDenominator(QuantityType value) {
      this.denominator = value;
   }
}
