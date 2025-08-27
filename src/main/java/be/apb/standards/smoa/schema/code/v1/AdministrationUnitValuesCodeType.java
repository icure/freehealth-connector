package be.apb.standards.smoa.schema.code.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdministrationUnitValuesCodeType",
   propOrder = {"cd"}
)
public class AdministrationUnitValuesCodeType extends AbstractAdministrationUnitValuesCodeType {
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String cd;

   public String getCd() {
      return this.cd;
   }

   public void setCd(String value) {
      this.cd = value;
   }
}
