package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.PharmacyList;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BvacEventType",
   propOrder = {"pharmacyList"}
)
public class BvacEventType extends AbstractEventType {
   @XmlElement(
      name = "PharmacyList",
      required = true
   )
   protected PharmacyList pharmacyList;

   public PharmacyList getPharmacyList() {
      return this.pharmacyList;
   }

   public void setPharmacyList(PharmacyList value) {
      this.pharmacyList = value;
   }
}
