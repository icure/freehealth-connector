package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPharmacyIdType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferencePharmacyType",
   propOrder = {"pharmacyId"}
)
public class ReferencePharmacyType extends AbstractPharmacyType {
   @XmlElement(
      required = true
   )
   protected AbstractPharmacyIdType pharmacyId;

   public AbstractPharmacyIdType getPharmacyId() {
      return this.pharmacyId;
   }

   public void setPharmacyId(AbstractPharmacyIdType value) {
      this.pharmacyId = value;
   }
}
