package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DataEntryRequest",
   propOrder = {"reference"}
)
public class DataEntryRequest extends AbstractDataEntry {
   @XmlElement(
      required = true
   )
   protected String reference;

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }
}
