package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDDAYPERIOD;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dayperiodType",
   propOrder = {"cd"}
)
public class DayperiodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDDAYPERIOD cd;

   public CDDAYPERIOD getCd() {
      return this.cd;
   }

   public void setCd(CDDAYPERIOD value) {
      this.cd = value;
   }
}
