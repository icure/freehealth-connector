package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDSEX;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SexCodeType",
   propOrder = {"code"}
)
public class SexCodeType extends AbstractSexCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDSEX code;

   public CDSEX getCode() {
      return this.code;
   }

   public void setCode(CDSEX value) {
      this.code = value;
   }
}
