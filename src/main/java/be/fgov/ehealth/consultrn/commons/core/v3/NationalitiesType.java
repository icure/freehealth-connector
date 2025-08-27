package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalitiesType",
   propOrder = {"nationalities"}
)
public class NationalitiesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nationality",
      required = true
   )
   protected List<NationalityType> nationalities;

   public List<NationalityType> getNationalities() {
      if (this.nationalities == null) {
         this.nationalities = new ArrayList();
      }

      return this.nationalities;
   }
}
