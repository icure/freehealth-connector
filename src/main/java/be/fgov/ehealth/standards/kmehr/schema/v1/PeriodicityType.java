package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDPERIODICITY;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "periodicityType",
   propOrder = {"cd"}
)
public class PeriodicityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDPERIODICITY cd;

   public CDPERIODICITY getCd() {
      return this.cd;
   }

   public void setCd(CDPERIODICITY value) {
      this.cd = value;
   }
}
