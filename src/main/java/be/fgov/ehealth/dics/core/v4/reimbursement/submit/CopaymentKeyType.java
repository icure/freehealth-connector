package be.fgov.ehealth.dics.core.v4.reimbursement.submit;

import be.fgov.ehealth.dics.protocol.v4.ConsultCopaymentType;
import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CopaymentKeyType"
)
@XmlSeeAlso({ConsultCopaymentType.class})
public class CopaymentKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "RegimeType",
      required = true
   )
   protected BigInteger regimeType;

   public BigInteger getRegimeType() {
      return this.regimeType;
   }

   public void setRegimeType(BigInteger value) {
      this.regimeType = value;
   }
}
