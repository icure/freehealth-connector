package be.apb.gfddpp.productfilter;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "medicines",
   propOrder = {"medicine"}
)
public class Medicines {
   protected List<Medicine> medicine;

   public List<Medicine> getMedicine() {
      if (this.medicine == null) {
         this.medicine = new ArrayList();
      }

      return this.medicine;
   }
}
