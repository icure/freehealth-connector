package be.fgov.ehealth.etkra.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"distinguishedName", "signedCredentials"}
)
@XmlRootElement(
   name = "RevokeAndActivateRequest"
)
public class RevokeAndActivateRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DistinguishedName",
      required = true
   )
   protected EhealthDistinguishedNameType distinguishedName;
   @XmlElement(
      name = "SignedCredentials",
      required = true
   )
   protected SignedCredentialsType signedCredentials;

   public EhealthDistinguishedNameType getDistinguishedName() {
      return this.distinguishedName;
   }

   public void setDistinguishedName(EhealthDistinguishedNameType value) {
      this.distinguishedName = value;
   }

   public SignedCredentialsType getSignedCredentials() {
      return this.signedCredentials;
   }

   public void setSignedCredentials(SignedCredentialsType value) {
      this.signedCredentials = value;
   }
}
