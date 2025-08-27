package be.apb.standards.smoa.schema.code.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PharmFormCodeType",
   propOrder = {"value"}
)
public class PharmFormCodeType extends AbstractPharmFormCodeType {
   @XmlElement(
      required = true
   )
   protected String value;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
