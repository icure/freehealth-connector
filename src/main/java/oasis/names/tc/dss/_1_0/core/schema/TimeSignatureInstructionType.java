package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TimeSignatureInstructionType"
)
public class TimeSignatureInstructionType extends UpdateSignatureInstructionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "TimeStampTheGivenSignature"
   )
   protected Boolean timeStampTheGivenSignature;

   public boolean isTimeStampTheGivenSignature() {
      return this.timeStampTheGivenSignature == null ? false : this.timeStampTheGivenSignature;
   }

   public void setTimeStampTheGivenSignature(Boolean value) {
      this.timeStampTheGivenSignature = value;
   }
}
