package be.apb.gfddpp.rtrn.registerdata;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "bvacDocument",
   propOrder = {"dguid"}
)
public class BvacDocument {
   @XmlElement(
      required = true
   )
   protected String dguid;

   public String getDguid() {
      return this.dguid;
   }

   public void setDguid(String value) {
      this.dguid = value;
   }
}
