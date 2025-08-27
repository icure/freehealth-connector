package be.fgov.ehealth.etkra.protocol.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"challenge", "signature", "certificate"}
)
@XmlRootElement(
   name = "StartEtkRegistrationResponse"
)
public class StartEtkRegistrationResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Challenge"
   )
   protected byte[] challenge;
   @XmlElement(
      name = "Signature"
   )
   protected byte[] signature;
   @XmlElement(
      name = "Certificate"
   )
   protected byte[] certificate;

   public byte[] getChallenge() {
      return this.challenge;
   }

   public void setChallenge(byte[] value) {
      this.challenge = value;
   }

   public byte[] getSignature() {
      return this.signature;
   }

   public void setSignature(byte[] value) {
      this.signature = value;
   }

   public byte[] getCertificate() {
      return this.certificate;
   }

   public void setCertificate(byte[] value) {
      this.certificate = value;
   }
}
