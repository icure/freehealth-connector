package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsuredStatus",
   propOrder = {"cg1", "cg2"}
)
public class InsuredStatus {
   @XmlElement(
      defaultValue = "000"
   )
   protected String cg1;
   @XmlElement(
      defaultValue = "000"
   )
   protected String cg2;

   public String getCg1() {
      return this.cg1;
   }

   public void setCg1(String value) {
      this.cg1 = value;
   }

   public String getCg2() {
      return this.cg2;
   }

   public void setCg2(String value) {
      this.cg2 = value;
   }
}
