package be.cin.mycarenet.esb.common.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NihiiType",
   propOrder = {"quality", "value"}
)
public class NihiiType extends SelfRefType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quality"
   )
   protected String quality;
   @XmlElement(
      name = "Value"
   )
   protected ValueRefString value;

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }

   public ValueRefString getValue() {
      return this.value;
   }

   public void setValue(ValueRefString value) {
      this.value = value;
   }
}
