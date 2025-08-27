package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacksParam",
   propOrder = {"readFlag", "symmKey"}
)
@XmlRootElement(
   name = "listFeedbacksParam"
)
public class ListFeedbacksParam extends PartyIdentification {
   protected boolean readFlag;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;

   public boolean getReadFlag() {
      return this.readFlag;
   }

   public void setReadFlag(boolean value) {
      this.readFlag = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

}
