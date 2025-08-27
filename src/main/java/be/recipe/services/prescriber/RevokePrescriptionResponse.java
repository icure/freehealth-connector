package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "revokePrescriptionResponse",
   propOrder = {"revokePrescriptionResultSealed"}
)
@XmlRootElement(
   name = "revokePrescriptionResponse"
)
public class RevokePrescriptionResponse {
   @XmlElement(
      name = "RevokePrescriptionResultSealed"
   )
   protected byte[] revokePrescriptionResultSealed;

   public byte[] getRevokePrescriptionResultSealed() {
      return this.revokePrescriptionResultSealed;
   }

   public void setRevokePrescriptionResultSealed(byte[] value) {
      this.revokePrescriptionResultSealed = value;
   }

}
