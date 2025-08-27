package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HealthCareProfessionalType",
   propOrder = {"professions"}
)
@XmlRootElement(
   name = "HealthCareProfessional"
)
public class HealthCareProfessional extends IndividualType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Profession",
      required = true
   )
   protected List<ProfessionV3> professions;

   public List<ProfessionV3> getProfessions() {
      if (this.professions == null) {
         this.professions = new ArrayList();
      }

      return this.professions;
   }
}
