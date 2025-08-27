package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptedContentType",
   propOrder = {"product", "encryptionKeyId"}
)
public class EncryptedContentType {
   @XmlElement(
      required = true
   )
   protected byte[] product;
   @XmlElement(
      required = true
   )
   protected String encryptionKeyId;

   public byte[] getProduct() {
      return this.product;
   }

   public void setProduct(byte[] value) {
      this.product = value;
   }

   public String getEncryptionKeyId() {
      return this.encryptionKeyId;
   }

   public void setEncryptionKeyId(String value) {
      this.encryptionKeyId = value;
   }
}
