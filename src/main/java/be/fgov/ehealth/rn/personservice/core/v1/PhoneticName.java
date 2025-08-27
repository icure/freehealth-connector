package be.fgov.ehealth.rn.personservice.core.v1;

import be.fgov.ehealth.rn.baselegaldata.v1.GivenNameType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticName",
   propOrder = {"lastName", "givenNames", "givenNameMatching"}
)
public class PhoneticName implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LastName",
      required = true
   )
   protected String lastName;
   @XmlElement(
      name = "GivenName",
      required = true
   )
   protected List<GivenNameType> givenNames;
   @XmlElement(
      name = "GivenNameMatching",
      required = true
   )
   protected String givenNameMatching;

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public List<GivenNameType> getGivenNames() {
      if (this.givenNames == null) {
         this.givenNames = new ArrayList();
      }

      return this.givenNames;
   }

   public String getGivenNameMatching() {
      return this.givenNameMatching;
   }

   public void setGivenNameMatching(String value) {
      this.givenNameMatching = value;
   }
}
