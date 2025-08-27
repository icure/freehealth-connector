package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "revokePrescriptionParam",
   propOrder = {"rid", "reason", "symmKey"}
)
@XmlRootElement(
   name = "revokePrescriptionParam"
)
public class RevokePrescriptionParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   protected String reason;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public String getReason() {
      return this.reason;
   }

   public void setReason(String value) {
      this.reason = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

}
