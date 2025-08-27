package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionForPrescriber",
   propOrder = {"getPrescriptionForPrescriberParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "getPrescriptionForPrescriber"
)
public class GetPrescriptionForPrescriber implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetPrescriptionForPrescriberParamSealed"
   )
   protected byte[] getPrescriptionForPrescriberParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getGetPrescriptionForPrescriberParamSealed() {
      return this.getPrescriptionForPrescriberParamSealed;
   }

   public void setGetPrescriptionForPrescriberParamSealed(byte[] value) {
      this.getPrescriptionForPrescriberParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
