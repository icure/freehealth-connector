package be.fgov.ehealth.dics.core.v4.refdata;

import be.fgov.ehealth.dics.protocol.v4.StandardUnitFamhpType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StandardUnitKeyFamhpType"
)
@XmlSeeAlso({StandardUnitFamhpType.class})
public class StandardUnitKeyFamhpType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Name",
      required = true
   )
   protected String name;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
