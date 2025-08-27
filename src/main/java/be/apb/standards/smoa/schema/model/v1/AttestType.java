package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractAttestIdType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttestType",
   propOrder = {"id", "description"}
)
public class AttestType extends AbstractAttestType {
   @XmlElement(
      required = true
   )
   protected AbstractAttestIdType id;
   protected String description;

   public AbstractAttestIdType getId() {
      return this.id;
   }

   public void setId(AbstractAttestIdType value) {
      this.id = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }
}
