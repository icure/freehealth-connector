package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createPrescriptionResponse",
   propOrder = {"createPrescriptionResultSealed"}
)
@XmlRootElement(
   name = "createPrescriptionResponse"
)
public class CreatePrescriptionResponse {
   @XmlElement(
      name = "CreatePrescriptionResultSealed"
   )
   protected byte[] createPrescriptionResultSealed;

   public byte[] getCreatePrescriptionResultSealed() {
      return this.createPrescriptionResultSealed;
   }

   public void setCreatePrescriptionResultSealed(byte[] value) {
      this.createPrescriptionResultSealed = value;
   }
}
