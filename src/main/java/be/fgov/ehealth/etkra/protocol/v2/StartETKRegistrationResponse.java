package be.fgov.ehealth.etkra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StartETKRegistrationResponseType",
   propOrder = {"challenge"}
)
@XmlRootElement(
   name = "StartETKRegistrationResponse"
)
public class StartETKRegistrationResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Challenge"
   )
   protected byte[] challenge;

   public byte[] getChallenge() {
      return this.challenge;
   }

   public void setChallenge(byte[] value) {
      this.challenge = value;
   }
}
