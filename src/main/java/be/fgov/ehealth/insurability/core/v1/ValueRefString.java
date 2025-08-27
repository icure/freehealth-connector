package be.fgov.ehealth.insurability.core.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValueRefString",
   propOrder = {"value"}
)
public class ValueRefString {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "ValueRef"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String valueRef;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getValueRef() {
      return this.valueRef;
   }

   public void setValueRef(String value) {
      this.valueRef = value;
   }
}
