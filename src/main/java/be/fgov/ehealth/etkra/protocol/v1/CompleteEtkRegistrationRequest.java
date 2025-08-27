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
   propOrder = {"toBeRegistered", "signedCredentials"}
)
@XmlRootElement(
   name = "CompleteEtkRegistrationRequest"
)
public class CompleteEtkRegistrationRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ToBeRegistered",
      required = true
   )
   protected byte[] toBeRegistered;
   @XmlElement(
      name = "SignedCredentials"
   )
   protected SignedCredentialsType signedCredentials;

   public byte[] getToBeRegistered() {
      return this.toBeRegistered;
   }

   public void setToBeRegistered(byte[] value) {
      this.toBeRegistered = value;
   }

   public SignedCredentialsType getSignedCredentials() {
      return this.signedCredentials;
   }

   public void setSignedCredentials(SignedCredentialsType value) {
      this.signedCredentials = value;
   }
}
