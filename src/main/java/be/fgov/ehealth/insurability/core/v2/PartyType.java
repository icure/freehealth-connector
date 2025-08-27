package be.fgov.ehealth.insurability.core.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PartyType",
   propOrder = {"physicalPerson", "organization"}
)
public class PartyType {
   @XmlElement(
      name = "PhysicalPerson"
   )
   protected IdType physicalPerson;
   @XmlElement(
      name = "Organization"
   )
   protected IdType organization;

   public IdType getPhysicalPerson() {
      return this.physicalPerson;
   }

   public void setPhysicalPerson(IdType value) {
      this.physicalPerson = value;
   }

   public IdType getOrganization() {
      return this.organization;
   }

   public void setOrganization(IdType value) {
      this.organization = value;
   }
}
