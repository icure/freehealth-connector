package be.apb.gfddpp.rtrn.registerdata;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "magistral",
   propOrder = {"magistralIds"}
)
public class Magistral {
   @XmlElement(
      required = true
   )
   protected List<String> magistralIds;

   public List<String> getMagistralIds() {
      if (this.magistralIds == null) {
         this.magistralIds = new ArrayList();
      }

      return this.magistralIds;
   }
}
