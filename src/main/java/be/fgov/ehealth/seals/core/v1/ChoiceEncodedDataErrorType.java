package be.fgov.ehealth.seals.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ChoiceEncodedDataErrorType",
   propOrder = {"error", "encodedData"}
)
public class ChoiceEncodedDataErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Error"
   )
   protected ErrorType error;
   @XmlElement(
      name = "EncodedData"
   )
   protected EncodedDataType encodedData;

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }

   public EncodedDataType getEncodedData() {
      return this.encodedData;
   }

   public void setEncodedData(EncodedDataType value) {
      this.encodedData = value;
   }
}
