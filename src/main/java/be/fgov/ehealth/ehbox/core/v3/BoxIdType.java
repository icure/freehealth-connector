package be.fgov.ehealth.ehbox.core.v3;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.SubstituteType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BoxIdType",
   propOrder = {"quality"}
)
@XmlSeeAlso({SubstituteType.class})
public class BoxIdType extends IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quality",
      required = true
   )
   protected String quality;

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }
}
