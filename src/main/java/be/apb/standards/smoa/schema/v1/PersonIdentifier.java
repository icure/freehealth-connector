package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonIdentifier",
   propOrder = {"idType", "idValue"}
)
public class PersonIdentifier {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected PersonIdType idType;
   @XmlElement(
      required = true
   )
   protected String idValue;

   public PersonIdType getIdType() {
      return this.idType;
   }

   public void setIdType(PersonIdType value) {
      this.idType = value;
   }

   public String getIdValue() {
      return this.idValue;
   }

   public void setIdValue(String value) {
      this.idValue = value;
   }
}
