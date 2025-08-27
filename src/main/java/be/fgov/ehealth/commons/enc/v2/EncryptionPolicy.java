package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"keyInfo"}
)
@XmlRootElement(
   name = "EncryptionPolicy"
)
public class EncryptionPolicy implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "KeyInfo",
      required = true
   )
   protected KeyInfo keyInfo;
   @XmlAttribute(
      name = "MimeType",
      required = true
   )
   protected String mimeType;

   public KeyInfo getKeyInfo() {
      return this.keyInfo;
   }

   public void setKeyInfo(KeyInfo value) {
      this.keyInfo = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }
}
