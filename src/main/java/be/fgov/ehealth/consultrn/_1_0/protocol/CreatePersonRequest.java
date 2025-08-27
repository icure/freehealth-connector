package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.PersonDataType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePersonRequestType",
   propOrder = {"personData"}
)
@XmlRootElement(
   name = "CreatePersonRequest"
)
public class CreatePersonRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PersonData",
      required = true
   )
   protected PersonDataType personData;

   public PersonDataType getPersonData() {
      return this.personData;
   }

   public void setPersonData(PersonDataType value) {
      this.personData = value;
   }
}
