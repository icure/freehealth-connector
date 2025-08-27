package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractAttestIdType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceAttestType",
   propOrder = {"attestId"}
)
public class ReferenceAttestType extends AbstractAttestType {
   @XmlElement(
      required = true
   )
   protected AbstractAttestIdType attestId;

   public AbstractAttestIdType getAttestId() {
      return this.attestId;
   }

   public void setAttestId(AbstractAttestIdType value) {
      this.attestId = value;
   }
}
