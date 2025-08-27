package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "putVisionResponse",
   propOrder = {"putVisionResultSealed"}
)
@XmlRootElement(
   name = "putVisionResponse"
)
public class PutVisionResponse {
   @XmlElement(
      name = "PutVisionResultSealed"
   )
   protected byte[] putVisionResultSealed;

   public byte[] getPutVisionResultSealed() {
      return this.putVisionResultSealed;
   }

   public void setPutVisionResultSealed(byte[] value) {
      this.putVisionResultSealed = value;
   }

}
