package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DigestType"
)
@XmlRootElement(
   name = "Digest"
)
public class Digest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "algorithm",
      required = true
   )
   protected String algorithm;

   public String getAlgorithm() {
      return this.algorithm;
   }

   public void setAlgorithm(String value) {
      this.algorithm = value;
   }
}
