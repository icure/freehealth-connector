package be.fgov.ehealth.commons.enc.v2;

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
   protected CipherValue cipherValue;

   public CipherReference getCipherReference() {
      return this.cipherReference;
   }

   public void setCipherReference(CipherReference value) {
      this.cipherReference = value;
   }

   public CipherValue getCipherValue() {
      return this.cipherValue;
   }

   public void setCipherValue(CipherValue value) {
      this.cipherValue = value;
   }
}
