package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDCOUNTRY;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CountryType",
   propOrder = {"code"}
)
public class CountryType extends AbstractCountryCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDCOUNTRY code;

   public CDCOUNTRY getCode() {
      return this.code;
   }

   public void setCode(CDCOUNTRY value) {
      this.code = value;
   }
}
