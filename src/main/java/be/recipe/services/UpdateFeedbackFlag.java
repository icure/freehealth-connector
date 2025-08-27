package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "updateFeedbackFlag",
   propOrder = {"updateFeedbackFlagParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "updateFeedbackFlag"
)
public class UpdateFeedbackFlag implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "UpdateFeedbackFlagParamSealed"
   )
   protected byte[] updateFeedbackFlagParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getUpdateFeedbackFlagParamSealed() {
      return this.updateFeedbackFlagParamSealed;
   }

   public void setUpdateFeedbackFlagParamSealed(byte[] value) {
      this.updateFeedbackFlagParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
