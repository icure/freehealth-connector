package be.apb.gfddpp.assuralia.batch;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"bvac"}
)
@XmlRootElement(
   name = "BvacList"
)
public class BvacList {
   @XmlElement(
      name = "Bvac",
      required = true
   )
   protected List<Bvac> bvac;

   public List<Bvac> getBvac() {
      if (this.bvac == null) {
         this.bvac = new ArrayList();
      }

      return this.bvac;
   }
}
