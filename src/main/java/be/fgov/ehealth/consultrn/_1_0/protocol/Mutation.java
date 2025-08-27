package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.PersonType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MutationType",
   propOrder = {"mutationInformation", "person"}
)
@XmlRootElement(
   name = "Mutation"
)
public class Mutation implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MutationInformation",
      required = true
   )
   protected MutationInformationType mutationInformation;
   @XmlElement(
      name = "Person",
      required = true
   )
   protected PersonType person;

   public MutationInformationType getMutationInformation() {
      return this.mutationInformation;
   }

   public void setMutationInformation(MutationInformationType value) {
      this.mutationInformation = value;
   }

   public PersonType getPerson() {
      return this.person;
   }

   public void setPerson(PersonType value) {
      this.person = value;
   }
}
