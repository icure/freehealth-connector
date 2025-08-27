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
   propOrder = {"signedCertificate"}
)
@XmlRootElement(
   name = "GetValidAuthCertsResponse"
)
public class GetValidAuthCertsResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedCertificate"
   )
   protected byte[] signedCertificate;

   public byte[] getSignedCertificate() {
      return this.signedCertificate;
   }

   public void setSignedCertificate(byte[] value) {
      this.signedCertificate = value;
   }
}
