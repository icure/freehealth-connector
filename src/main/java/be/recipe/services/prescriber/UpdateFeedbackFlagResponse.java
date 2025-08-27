package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "updateFeedbackFlagResponse",
   propOrder = {"updateFeedbackFlagResultSealed"}
)
@XmlRootElement(
   name = "updateFeedbackFlagResponse"
)
public class UpdateFeedbackFlagResponse {
   @XmlElement(
      name = "UpdateFeedbackFlagResultSealed"
   )
   protected byte[] updateFeedbackFlagResultSealed;

   public byte[] getUpdateFeedbackFlagResultSealed() {
      return this.updateFeedbackFlagResultSealed;
   }

   public void setUpdateFeedbackFlagResultSealed(byte[] value) {
      this.updateFeedbackFlagResultSealed = value;
   }

}
