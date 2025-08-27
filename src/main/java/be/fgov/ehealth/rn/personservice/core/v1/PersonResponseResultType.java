package be.fgov.ehealth.rn.personservice.core.v1;

import be.fgov.ehealth.rn.personlegaldata.v1.PersonResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonResponseResultType",
   propOrder = {"person"}
)
public class PersonResponseResultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Person",
      required = true
   )
   protected PersonResponseType person;

   public PersonResponseType getPerson() {
      return this.person;
   }

   public void setPerson(PersonResponseType value) {
      this.person = value;
   }
}
