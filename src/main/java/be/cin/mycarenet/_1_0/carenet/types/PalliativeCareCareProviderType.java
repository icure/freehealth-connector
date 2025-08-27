package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PalliativeCareCareProviderType",
   propOrder = {"nurse", "multidisciplinaryTeam"}
)
public class PalliativeCareCareProviderType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nurse"
   )
   protected String nurse;
   @XmlElement(
      name = "MultidisciplinaryTeam"
   )
   protected String multidisciplinaryTeam;

   public String getNurse() {
      return this.nurse;
   }

   public void setNurse(String value) {
      this.nurse = value;
   }

   public String getMultidisciplinaryTeam() {
      return this.multidisciplinaryTeam;
   }

   public void setMultidisciplinaryTeam(String value) {
      this.multidisciplinaryTeam = value;
   }
}
