package be.fgov.ehealth.etee.kgss._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"keyIdentifier", "keyEncryptionKey", "keyEncryptionKeyIdentifier", "etk"}
)
@XmlRootElement(
   name = "GetKeyRequestContent"
)
public class GetKeyRequestContent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "KeyIdentifier",
      required = true
   )
   protected byte[] keyIdentifier;
   @XmlElement(
      name = "KeyEncryptionKey"
   )
   protected byte[] keyEncryptionKey;
   @XmlElement(
      name = "KeyEncryptionKeyIdentifier"
   )
   protected byte[] keyEncryptionKeyIdentifier;
   @XmlElement(
      name = "ETK"
   )
   protected byte[] etk;

   public byte[] getKeyIdentifier() {
      return this.keyIdentifier;
   }

   public void setKeyIdentifier(byte[] value) {
      this.keyIdentifier = value;
   }

   public byte[] getKeyEncryptionKey() {
      return this.keyEncryptionKey;
   }

   public void setKeyEncryptionKey(byte[] value) {
      this.keyEncryptionKey = value;
   }

   public byte[] getKeyEncryptionKeyIdentifier() {
      return this.keyEncryptionKeyIdentifier;
   }

   public void setKeyEncryptionKeyIdentifier(byte[] value) {
      this.keyEncryptionKeyIdentifier = value;
   }

   public byte[] getETK() {
      return this.etk;
   }

   public void setETK(byte[] value) {
      this.etk = value;
   }
}
