package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubstanceProductId",
   propOrder = {"inn"}
)
public class SubstanceProductId extends AbstractSubstanceProductIdType {
   @XmlElement(
      required = true
   )
   protected String inn;

   public String getInn() {
      return this.inn;
   }

   public void setInn(String value) {
      this.inn = value;
   }
}
