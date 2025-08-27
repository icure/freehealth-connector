package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DescriptionBvacProduct",
   propOrder = {"fullDescription"}
)
public class DescriptionBvacProduct {
   @XmlElement(
      required = true
   )
   protected String fullDescription;

   public String getFullDescription() {
      return this.fullDescription;
   }

   public void setFullDescription(String value) {
      this.fullDescription = value;
   }
}
