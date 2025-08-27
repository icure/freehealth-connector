package be.fgov.ehealth.rn.cbsspersonservice.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExistingPersons",
   propOrder = {"existingPersons"}
)
public class ExistingPersons implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ExistingPerson",
      required = true
   )
   protected List existingPersons;

   public List getExistingPersons() {
      if (this.existingPersons == null) {
         this.existingPersons = new ArrayList();
      }

      return this.existingPersons;
   }
}
