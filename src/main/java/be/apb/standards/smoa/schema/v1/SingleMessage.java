package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"unsigned", "signed"}
)
@XmlRootElement(
   name = "single-message"
)
public class SingleMessage {
   protected SmoaMessageType unsigned;
   protected DigitalSignedSmoaMessageType signed;

   public SmoaMessageType getUnsigned() {
      return this.unsigned;
   }

   public void setUnsigned(SmoaMessageType value) {
      this.unsigned = value;
   }

   public DigitalSignedSmoaMessageType getSigned() {
      return this.signed;
   }

   public void setSigned(DigitalSignedSmoaMessageType value) {
      this.signed = value;
   }
}
