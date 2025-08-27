package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationPropertiesResponse",
   propOrder = {"validationPropertiesResultSealed"}
)
@XmlRootElement(
   name = "validationPropertiesResponse"
)
public class ValidationPropertiesResponse {
   @XmlElement(
      name = "ValidationPropertiesResultSealed"
   )
   protected byte[] validationPropertiesResultSealed;

   public byte[] getValidationPropertiesResultSealed() {
      return this.validationPropertiesResultSealed;
   }

   public void setValidationPropertiesResultSealed(byte[] value) {
      this.validationPropertiesResultSealed = value;
   }

}
