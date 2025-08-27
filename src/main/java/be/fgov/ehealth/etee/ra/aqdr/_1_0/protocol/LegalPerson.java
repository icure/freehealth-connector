package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LegalPerson",
   propOrder = {"organizations", "aaOrganizationAuth"}
)
public class LegalPerson implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Organization",
      required = true
   )
   protected List<Organization> organizations;
   @XmlElement(
      name = "AAOrganizationAuth",
      required = true
   )
   protected byte[] aaOrganizationAuth;

   public List<Organization> getOrganizations() {
      if (this.organizations == null) {
         this.organizations = new ArrayList();
      }

      return this.organizations;
   }

   public byte[] getAAOrganizationAuth() {
      return this.aaOrganizationAuth;
   }

   public void setAAOrganizationAuth(byte[] value) {
      this.aaOrganizationAuth = value;
   }
}
