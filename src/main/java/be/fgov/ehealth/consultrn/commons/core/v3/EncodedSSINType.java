package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncodedSSINType",
   propOrder = {"value"}
)
public class EncodedSSINType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Encoded"
   )
   protected Boolean encoded;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public Boolean isEncoded() {
      return this.encoded;
   }

   public void setEncoded(Boolean value) {
      this.encoded = value;
   }
}
