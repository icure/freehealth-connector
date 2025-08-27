package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentificationBvacDocument",
        propOrder = {"id", "internalReference", "barCode"}
)
public class IdentificationBvacDocument {
   @XmlElement(
      required = true
   )
   protected String id;
   @XmlElement(
           name = "InternalReference"
   )
   protected String internalReference;
   @XmlElement(
      required = true
   )
   protected BarCode barCode;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getInternalReference() {
      return this.internalReference;
   }

   public void setInternalReference(String value) {
      this.internalReference = value;
   }

   public BarCode getBarCode() {
      return this.barCode;
   }

   public void setBarCode(BarCode value) {
      this.barCode = value;
   }
}
