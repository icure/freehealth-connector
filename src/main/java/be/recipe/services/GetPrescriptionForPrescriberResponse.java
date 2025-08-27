package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForPrescriberResponse",
   propOrder = {"getPrescriptionForPrescriberResultSealed"}
)
@XmlRootElement(
   name = "getPrescriptionForPrescriberResponse"
)
public class GetPrescriptionForPrescriberResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetPrescriptionForPrescriberResultSealed"
   )
   protected byte[] getPrescriptionForPrescriberResultSealed;

   public byte[] getGetPrescriptionForPrescriberResultSealed() {
      return this.getPrescriptionForPrescriberResultSealed;
   }

   public void setGetPrescriptionForPrescriberResultSealed(byte[] value) {
      this.getPrescriptionForPrescriberResultSealed = (byte[])value;
   }
}
