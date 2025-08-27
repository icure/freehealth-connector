package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDADDRESS;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressKindCodeType",
   propOrder = {"usage"}
)
public class AddressKindCodeType extends AbstractAddressKindCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDADDRESS usage;

   public CDADDRESS getUsage() {
      return this.usage;
   }

   public void setUsage(CDADDRESS value) {
      this.usage = value;
   }
}
