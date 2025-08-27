package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listRidsHistoryResponse",
   propOrder = {"listRidsHistoryResultSealed"}
)
@XmlRootElement(
   name = "listRidsHistoryResponse"
)
public class ListRidsHistoryResponse {
   @XmlElement(
      name = "ListRidsHistoryResultSealed"
   )
   protected byte[] listRidsHistoryResultSealed;

   public byte[] getListRidsHistoryResultSealed() {
      return this.listRidsHistoryResultSealed;
   }

   public void setListRidsHistoryResultSealed(byte[] value) {
      this.listRidsHistoryResultSealed = value;
   }
}
