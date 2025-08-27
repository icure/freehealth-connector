package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BirthRequestType",
   propOrder = {"birthDate", "birthPlace"}
)
public class BirthRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BirthDate",
      required = true
   )
   protected String birthDate;
   @XmlElement(
      name = "BirthPlace"
   )
   protected WhereRequestType birthPlace;

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }

   public WhereRequestType getBirthPlace() {
      return this.birthPlace;
   }

   public void setBirthPlace(WhereRequestType value) {
      this.birthPlace = value;
   }
}
