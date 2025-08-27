package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OpenQuarterPeriodType",
   propOrder = {"beginQuarter", "endQuarter"}
)
public class OpenQuarterPeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BeginQuarter",
      required = true
   )
   protected String beginQuarter;
   @XmlElement(
      name = "EndQuarter"
   )
   protected String endQuarter;

   public String getBeginQuarter() {
      return this.beginQuarter;
   }

   public void setBeginQuarter(String value) {
      this.beginQuarter = value;
   }

   public String getEndQuarter() {
      return this.endQuarter;
   }

   public void setEndQuarter(String value) {
      this.endQuarter = value;
   }
}
