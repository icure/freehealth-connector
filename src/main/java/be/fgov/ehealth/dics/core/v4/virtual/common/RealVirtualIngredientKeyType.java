package be.fgov.ehealth.dics.core.v4.virtual.common;

import be.fgov.ehealth.dics.protocol.v4.ConsultRealVirtualIngredientType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RealVirtualIngredientKeyType"
)
@XmlSeeAlso({ConsultRealVirtualIngredientType.class})
public class RealVirtualIngredientKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "SequenceNr",
      required = true
   )
   protected short sequenceNr;

   public short getSequenceNr() {
      return this.sequenceNr;
   }

   public void setSequenceNr(short value) {
      this.sequenceNr = value;
   }
}
