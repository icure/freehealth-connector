package be.apb.standards.smoa.schema.code.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SpecialPrescriptionCodeType",
   propOrder = {"type"}
)
public class SpecialPrescriptionCodeType extends AbstractSpecialPrescriptionCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected SPECIALPRESCRIPTIONTYPE type;

   public SPECIALPRESCRIPTIONTYPE getType() {
      return this.type;
   }

   public void setType(SPECIALPRESCRIPTIONTYPE value) {
      this.type = value;
   }
}
