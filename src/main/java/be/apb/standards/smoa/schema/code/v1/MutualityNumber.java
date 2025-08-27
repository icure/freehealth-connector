package be.apb.standards.smoa.schema.code.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MutualityNumber",
   propOrder = {"number"}
)
public class MutualityNumber extends AbstractMutualityNumberCodeType {
   @XmlElement(
      required = true
   )
   protected String number;

   public String getNumber() {
      return this.number;
   }

   public void setNumber(String value) {
      this.number = value;
   }
}
