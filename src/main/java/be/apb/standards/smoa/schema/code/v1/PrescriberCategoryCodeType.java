package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDHCPARTY;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PrescriberCategoryCodeType",
   propOrder = {"value"}
)
public class PrescriberCategoryCodeType extends AbstractPrescriberCategoryCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDHCPARTY value;

   public CDHCPARTY getValue() {
      return this.value;
   }

   public void setValue(CDHCPARTY value) {
      this.value = value;
   }
}
