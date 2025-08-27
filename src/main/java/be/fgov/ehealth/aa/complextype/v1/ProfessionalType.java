package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionalType",
   propOrder = {"specialities"}
)
public class ProfessionalType extends ProfessionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Speciality"
   )
   protected List<Speciality> specialities;

   public List<Speciality> getSpecialities() {
      if (this.specialities == null) {
         this.specialities = new ArrayList();
      }

      return this.specialities;
   }
}
