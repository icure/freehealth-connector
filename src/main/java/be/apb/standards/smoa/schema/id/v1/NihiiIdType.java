package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NihiiIdType",
   propOrder = {"nihiiPharmacyNumber"}
)
public class NihiiIdType extends AbstractPharmacyIdType {
   @XmlElement(
      required = true
   )
   protected String nihiiPharmacyNumber;

   public String getNihiiPharmacyNumber() {
      return this.nihiiPharmacyNumber;
   }

   public void setNihiiPharmacyNumber(String value) {
      this.nihiiPharmacyNumber = value;
   }
}
