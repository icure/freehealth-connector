package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForExecutorResponse",
   propOrder = {"getPrescriptionForExecutorResultSealed"}
)
@XmlRootElement(
   name = "getPrescriptionForExecutorResponse"
)
public class GetPrescriptionForExecutorResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetPrescriptionForExecutorResultSealed"
   )
   protected byte[] getPrescriptionForExecutorResultSealed;

   public byte[] getGetPrescriptionForExecutorResultSealed() {
      return this.getPrescriptionForExecutorResultSealed;
   }

   public void setGetPrescriptionForExecutorResultSealed(byte[] value) {
      this.getPrescriptionForExecutorResultSealed = (byte[])value;
   }
}
