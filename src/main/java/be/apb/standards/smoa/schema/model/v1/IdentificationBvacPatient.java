package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentificationBvacPatient",
   propOrder = {"id"}
)
public class IdentificationBvacPatient {
   @XmlElement(
      required = true
   )
   protected String id;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
