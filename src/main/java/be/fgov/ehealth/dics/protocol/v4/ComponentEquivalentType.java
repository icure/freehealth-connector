package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.core.QuantityType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ComponentEquivalentType",
   propOrder = {"content"}
)
public class ComponentEquivalentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Content",
      required = true
   )
   protected QuantityType content;

   public QuantityType getContent() {
      return this.content;
   }

   public void setContent(QuantityType value) {
      this.content = value;
   }
}
