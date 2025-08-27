package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDADDRESS;
import be.apb.standards.smoa.schema.v1.CDTELECOM;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TelecomKindCodeType",
   propOrder = {"type", "usage"}
)
public class TelecomKindCodeType extends AbstractTelecomKindCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDTELECOM type;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDADDRESS usage;

   public CDTELECOM getType() {
      return this.type;
   }

   public void setType(CDTELECOM value) {
      this.type = value;
   }

   public CDADDRESS getUsage() {
      return this.usage;
   }

   public void setUsage(CDADDRESS value) {
      this.usage = value;
   }
}
