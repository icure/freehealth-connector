package be.apb.gfddpp.rtrn.registerdata;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "continuedPharmaceuticalCareDossierEvent",
   propOrder = {"sguid", "dguid"}
)
public class ContinuedPharmaceuticalCareDossierEvent {
   @XmlElement(
      required = true
   )
   protected String sguid;
   @XmlElement(
      required = true
   )
   protected String dguid;

   public String getSguid() {
      return this.sguid;
   }

   public void setSguid(String value) {
      this.sguid = value;
   }

   public String getDguid() {
      return this.dguid;
   }

   public void setDguid(String value) {
      this.dguid = value;
   }
}
