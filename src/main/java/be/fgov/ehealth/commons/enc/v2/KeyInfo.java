package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KeyInfoType",
   propOrder = {"key", "encryptedKey", "etkSearchCriteria"}
)
@XmlRootElement(
   name = "KeyInfo"
)
public class KeyInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Key"
   )
   protected Key key;
   @XmlElement(
      name = "EncryptedKey"
   )
   protected EncryptedDataType encryptedKey;
   @XmlElement(
      name = "ETKSearchCriteria"
   )
   protected ETKSearchCriteria etkSearchCriteria;

   public Key getKey() {
      return this.key;
   }

   public void setKey(Key value) {
      this.key = value;
   }

   public EncryptedDataType getEncryptedKey() {
      return this.encryptedKey;
   }

   public void setEncryptedKey(EncryptedDataType value) {
      this.encryptedKey = value;
   }

   public ETKSearchCriteria getETKSearchCriteria() {
      return this.etkSearchCriteria;
   }

   public void setETKSearchCriteria(ETKSearchCriteria value) {
      this.etkSearchCriteria = value;
   }
}
