package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapyType",
   propOrder = {"maskedInd"}
)
@XmlSeeAlso({TherapyAndChildrenType.class})
public class TherapyType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MaskedInd",
      required = true
   )
   protected String maskedInd;

   public String getMaskedInd() {
      return this.maskedInd;
   }

   public void setMaskedInd(String value) {
      this.maskedInd = value;
   }
}
