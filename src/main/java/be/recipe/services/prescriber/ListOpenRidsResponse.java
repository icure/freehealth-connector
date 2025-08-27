package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenRidsResponse",
   propOrder = {"listOpenRidsResultSealed"}
)
@XmlRootElement(
   name = "listOpenRidsResponse"
)
public class ListOpenRidsResponse {
   @XmlElement(
      name = "ListOpenRidsResultSealed"
   )
   protected byte[] listOpenRidsResultSealed;

   public byte[] getListOpenRidsResultSealed() {
      return this.listOpenRidsResultSealed;
   }

   public void setListOpenRidsResultSealed(byte[] value) {
      this.listOpenRidsResultSealed = value;
   }
}
