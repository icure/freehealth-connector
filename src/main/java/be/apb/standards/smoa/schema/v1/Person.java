package be.apb.standards.smoa.schema.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Person",
   propOrder = {"personId", "firstName", "lastName", "role"}
)
public class Person extends AbstractAuthor {
   @XmlElement(
      required = true
   )
   protected List<PersonIdentifier> personId;
   protected String firstName;
   protected String lastName;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected PersonRoleType role;

   public List<PersonIdentifier> getPersonId() {
      if (this.personId == null) {
         this.personId = new ArrayList();
      }

      return this.personId;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public PersonRoleType getRole() {
      return this.role;
   }

   public void setRole(PersonRoleType value) {
      this.role = value;
   }
}
