package be.fgov.ehealth.chap4.core.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RecordCommonOutputType",
   propOrder = {"inputReference", "outputReference"}
)
public class RecordCommonOutputType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InputReference"
   )
   protected BigDecimal inputReference;
   @XmlElement(
      name = "OutputReference"
   )
   protected BigDecimal outputReference;

   public BigDecimal getInputReference() {
      return this.inputReference;
   }

   public void setInputReference(BigDecimal value) {
      this.inputReference = value;
   }

   public BigDecimal getOutputReference() {
      return this.outputReference;
   }

   public void setOutputReference(BigDecimal value) {
      this.outputReference = value;
   }
}
