package be.fgov.ehealth.aa.complextype.v1;

import be.fgov.ehealth.addressbook.core.v1.OrganizationContactInformationType;
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
   name = "OrganizationAddressbookType",
   propOrder = {"addresses", "healthCareAdditionalInformations"}
)
@XmlSeeAlso({OrganizationContactInformationType.class})
public class OrganizationAddressbookType extends HealthCareOrganizationTypeV2 implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Address"
   )
   protected List<Address> addresses;
   @XmlElement(
      name = "HealthCareAdditionalInformation"
   )
   protected List<HealthCareAdditionalInformation> healthCareAdditionalInformations;

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
