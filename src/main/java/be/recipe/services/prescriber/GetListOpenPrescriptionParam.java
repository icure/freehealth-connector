package be.recipe.services.prescriber;

import be.recipe.services.core.Page;
import be.recipe.services.core.PartyIdentification;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getListOpenPrescriptionParam",
   propOrder = {"symmKey", "page", "session"}
)
@XmlRootElement(
   name = "getListOpenPrescriptionParam"
)
public class GetListOpenPrescriptionParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   protected Page page;
   protected byte[] session;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public Page getPage() {
      return this.page;
   }

   public void setPage(Page value) {
      this.page = value;
   }

   public byte[] getSession() {
      return this.session;
   }

   public void setSession(byte[] value) {
      this.session = value;
   }

}
