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
   propOrder = {"distinguishedName"}
)
@XmlRootElement(
   name = "GetValidAuthCertsRequest"
)
public class GetValidAuthCertsRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DistinguishedName",
      required = true
   )
   protected EhealthDistinguishedNameType distinguishedName;

   public EhealthDistinguishedNameType getDistinguishedName() {
      return this.distinguishedName;
   }

   public void setDistinguishedName(EhealthDistinguishedNameType value) {
      this.distinguishedName = value;
   }
}
