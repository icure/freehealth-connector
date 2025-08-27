package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PharmacyList",
   propOrder = {"pharmacy"}
)
public class PharmacyList {
   @XmlElement(
      required = true
   )
   protected List<Pharmacy> pharmacy;

   public List<Pharmacy> getPharmacy() {
      if (this.pharmacy == null) {
         this.pharmacy = new ArrayList();
      }

      return this.pharmacy;
   }
}
