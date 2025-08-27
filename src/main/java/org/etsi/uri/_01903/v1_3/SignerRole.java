package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignerRoleType",
   propOrder = {"claimedRoles", "certifiedRoles"}
)
@XmlRootElement(
   name = "SignerRole"
)
public class SignerRole implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ClaimedRoles"
   )
   protected ClaimedRolesListType claimedRoles;
   @XmlElement(
      name = "CertifiedRoles"
   )
   protected CertifiedRolesListType certifiedRoles;

   public ClaimedRolesListType getClaimedRoles() {
      return this.claimedRoles;
   }

   public void setClaimedRoles(ClaimedRolesListType value) {
      this.claimedRoles = value;
   }

   public CertifiedRolesListType getCertifiedRoles() {
      return this.certifiedRoles;
   }

   public void setCertifiedRoles(CertifiedRolesListType value) {
      this.certifiedRoles = value;
   }
}
