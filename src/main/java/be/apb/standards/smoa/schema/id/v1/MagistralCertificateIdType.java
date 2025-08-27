package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MagistralCertificateIdType",
   propOrder = {"attestNumber"}
)
public class MagistralCertificateIdType extends AbstractMagistralCertificateIdType {
   @XmlElement(
      required = true
   )
   protected String attestNumber;

   public String getAttestNumber() {
      return this.attestNumber;
   }

   public void setAttestNumber(String value) {
      this.attestNumber = value;
   }
}
