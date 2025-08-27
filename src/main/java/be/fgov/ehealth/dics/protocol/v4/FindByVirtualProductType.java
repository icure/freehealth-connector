package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByVirtualProductType",
   propOrder = {"anyNamePart", "vmpCode"}
)
public class FindByVirtualProductType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AnyNamePart"
   )
   protected String anyNamePart;
   @XmlElement(
      name = "VmpCode"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger vmpCode;

   public String getAnyNamePart() {
      return this.anyNamePart;
   }

   public void setAnyNamePart(String value) {
      this.anyNamePart = value;
   }

   public BigInteger getVmpCode() {
      return this.vmpCode;
   }

   public void setVmpCode(BigInteger value) {
      this.vmpCode = value;
   }
}
