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
   name = "FindByGenericPrescriptionGroupType",
   propOrder = {"anyNamePart", "genericPrescriptionGroupCode"}
)
public class FindByGenericPrescriptionGroupType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AnyNamePart"
   )
   protected String anyNamePart;
   @XmlElement(
      name = "GenericPrescriptionGroupCode"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger genericPrescriptionGroupCode;

   public String getAnyNamePart() {
      return this.anyNamePart;
   }

   public void setAnyNamePart(String value) {
      this.anyNamePart = value;
   }

   public BigInteger getGenericPrescriptionGroupCode() {
      return this.genericPrescriptionGroupCode;
   }

   public void setGenericPrescriptionGroupCode(BigInteger value) {
      this.genericPrescriptionGroupCode = value;
   }
}
