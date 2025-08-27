package be.fgov.ehealth.dics.core.v3.core;

import be.fgov.ehealth.dics.protocol.v3.ConsultVmpType;
import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VmpKeyType"
)
@XmlSeeAlso({ConsultVmpType.class})
public class VmpKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger code;

   public BigInteger getCode() {
      return this.code;
   }

   public void setCode(BigInteger value) {
      this.code = value;
   }
}
