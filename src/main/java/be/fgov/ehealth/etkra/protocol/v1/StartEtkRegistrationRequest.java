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
   propOrder = {"publicEncryptionKey"}
)
@XmlRootElement(
   name = "StartEtkRegistrationRequest"
)
public class StartEtkRegistrationRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected byte[] publicEncryptionKey;

   public byte[] getPublicEncryptionKey() {
      return this.publicEncryptionKey;
   }

   public void setPublicEncryptionKey(byte[] value) {
      this.publicEncryptionKey = value;
   }
}
