package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "markAsArchived",
   propOrder = {"markAsArchivedParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "markAsArchived"
)
public class MarkAsArchived implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MarkAsArchivedParamSealed"
   )
   protected byte[] markAsArchivedParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getMarkAsArchivedParamSealed() {
      return this.markAsArchivedParamSealed;
   }

   public void setMarkAsArchivedParamSealed(byte[] value) {
      this.markAsArchivedParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
