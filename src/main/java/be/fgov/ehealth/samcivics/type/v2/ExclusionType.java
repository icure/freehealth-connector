package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExclusionType",
   propOrder = {"identifierNum"}
)
public class ExclusionType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IdentifierNum",
      required = true
   )
   protected IdentifierNumType identifierNum;

   public IdentifierNumType getIdentifierNum() {
      return this.identifierNum;
   }

   public void setIdentifierNum(IdentifierNumType value) {
      this.identifierNum = value;
   }
}
