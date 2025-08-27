package be.recipe.services;

import java.io.Serializable;
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
public class CreatePrescriptionResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CreatePrescriptionResultSealed"
   )
   protected byte[] createPrescriptionResultSealed;

   public byte[] getCreatePrescriptionResultSealed() {
      return this.createPrescriptionResultSealed;
   }

   public void setCreatePrescriptionResultSealed(byte[] value) {
      this.createPrescriptionResultSealed = (byte[])value;
   }
}
