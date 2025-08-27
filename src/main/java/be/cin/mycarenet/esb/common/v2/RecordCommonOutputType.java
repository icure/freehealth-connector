package be.cin.mycarenet.esb.common.v2;

import java.io.Serializable;
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
   protected String inputReference;
   @XmlElement(
      name = "OutputReference"
   )
   protected String outputReference;

   public String getInputReference() {
      return this.inputReference;
   }

   public void setInputReference(String value) {
      this.inputReference = value;
   }

   public String getOutputReference() {
      return this.outputReference;
   }

   public void setOutputReference(String value) {
      this.outputReference = value;
   }
}
