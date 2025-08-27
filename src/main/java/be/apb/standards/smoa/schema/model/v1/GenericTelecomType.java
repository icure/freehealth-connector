package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.TelecomKindCodeType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericTelecomType",
   propOrder = {"kind", "value", "lastChanged"}
)
public class GenericTelecomType extends AbstractTelecomType {
   @XmlElement(
      required = true
   )
   protected TelecomKindCodeType kind;
   @XmlElement(
      required = true
   )
   protected String value;
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar lastChanged;

   public TelecomKindCodeType getKind() {
      return this.kind;
   }

   public void setKind(TelecomKindCodeType value) {
      this.kind = value;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public XMLGregorianCalendar getLastChanged() {
      return this.lastChanged;
   }

   public void setLastChanged(XMLGregorianCalendar value) {
      this.lastChanged = value;
   }
}
