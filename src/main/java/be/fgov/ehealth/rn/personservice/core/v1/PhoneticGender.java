package be.fgov.ehealth.rn.personservice.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticGender",
   propOrder = {"genderCode"}
)
public class PhoneticGender implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenderCode",
      required = true
   )
   protected String genderCode;

   public String getGenderCode() {
      return this.genderCode;
   }

   public void setGenderCode(String value) {
      this.genderCode = value;
   }
}
