package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionStatusResponse",
   propOrder = {"getPrescriptionStatusResultSealed"}
)
@XmlRootElement(
   name = "getPrescriptionStatusResponse"
)
public class GetPrescriptionStatusResponse {
   @XmlElement(
      name = "GetPrescriptionStatusResultSealed"
   )
   protected byte[] getPrescriptionStatusResultSealed;

   public byte[] getGetPrescriptionStatusResultSealed() {
      return this.getPrescriptionStatusResultSealed;
   }

   public void setGetPrescriptionStatusResultSealed(byte[] value) {
      this.getPrescriptionStatusResultSealed = value;
   }

}
