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
   name = "FindByTherapeuticMoietyType",
   propOrder = {"therapeuticMoietyName", "therapeuticMoietyCode"}
)
public class FindByTherapeuticMoietyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TherapeuticMoietyName"
   )
   protected String therapeuticMoietyName;
   @XmlElement(
      name = "TherapeuticMoietyCode"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger therapeuticMoietyCode;

   public String getTherapeuticMoietyName() {
      return this.therapeuticMoietyName;
   }

   public void setTherapeuticMoietyName(String value) {
      this.therapeuticMoietyName = value;
   }

   public BigInteger getTherapeuticMoietyCode() {
      return this.therapeuticMoietyCode;
   }

   public void setTherapeuticMoietyCode(BigInteger value) {
      this.therapeuticMoietyCode = value;
   }
}
