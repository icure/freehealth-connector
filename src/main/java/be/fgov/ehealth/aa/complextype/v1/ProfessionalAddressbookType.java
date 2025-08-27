package be.fgov.ehealth.aa.complextype.v1;

import be.fgov.ehealth.addressbook.core.v1.ProfessionalInformation;
import be.fgov.ehealth.addressbook.core.v1.ProfessionalInformationType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionalAddressbookType",
   propOrder = {"profession", "addresses", "healthCareAdditionalInformations"}
)
@XmlSeeAlso({ProfessionalInformation.class, ProfessionalInformationType.class})
public class ProfessionalAddressbookType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Profession",
      required = true
   )
   protected ProfessionV3 profession;
   @XmlElement(
      name = "Address"
   )
   protected List<Address> addresses;
   @XmlElement(
      name = "HealthCareAdditionalInformation"
   )
   protected List<HealthCareAdditionalInformation> healthCareAdditionalInformations;

   public ProfessionV3 getProfession() {
      return this.profession;
   }

   public void setProfession(ProfessionV3 value) {
      this.profession = value;
   }

   public List<Address> getAddresses() {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      return this.addresses;
   }

   public List<HealthCareAdditionalInformation> getHealthCareAdditionalInformations() {
      if (this.healthCareAdditionalInformations == null) {
         this.healthCareAdditionalInformations = new ArrayList();
      }

      return this.healthCareAdditionalInformations;
   }
}
