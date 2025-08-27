package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CipherDataType",
   propOrder = {"cipherReference", "cipherValue"}
)
@XmlRootElement(
   name = "CipherData"
)
public class CipherData implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CipherReference"
   )
   protected CipherReference cipherReference;
   @XmlElement(
      name = "CipherValue"
   )
   protected byte[] cipherValue;

   public CipherReference getCipherReference() {
      return this.cipherReference;
   }

   public void setCipherReference(CipherReference value) {
      this.cipherReference = value;
   }

   public byte[] getCipherValue() {
      return this.cipherValue;
   }

   public void setCipherValue(byte[] value) {
      this.cipherValue = value;
   }
}
