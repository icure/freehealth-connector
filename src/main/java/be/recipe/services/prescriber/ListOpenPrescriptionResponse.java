package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenPrescriptionResponse",
   propOrder = {"getListOpenPrescriptionResultSealed"}
)
@XmlRootElement(
   name = "listOpenPrescriptionResponse"
)
public class ListOpenPrescriptionResponse {
   @XmlElement(
      name = "GetListOpenPrescriptionResultSealed"
   )
   protected byte[] getListOpenPrescriptionResultSealed;

   public byte[] getGetListOpenPrescriptionResultSealed() {
      return this.getListOpenPrescriptionResultSealed;
   }

   public void setGetListOpenPrescriptionResultSealed(byte[] value) {
      this.getListOpenPrescriptionResultSealed = value;
   }

}
