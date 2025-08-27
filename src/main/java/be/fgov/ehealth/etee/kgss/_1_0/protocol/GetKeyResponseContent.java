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
   propOrder = {"key"}
)
@XmlRootElement(
   name = "GetKeyResponseContent"
)
public class GetKeyResponseContent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Key"
   )
   protected byte[] key;

   public byte[] getKey() {
      return this.key;
   }

   public void setKey(byte[] value) {
      this.key = value;
   }
}
