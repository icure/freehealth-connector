package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Identity",
   propOrder = {"name"}
)
public class Identity {
   @XmlElement(
      required = true
   )
   protected String name;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
