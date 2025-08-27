package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDDAYPERIOD;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DayPeriodCodeType",
   propOrder = {"cd"}
)
public class DayPeriodCodeType extends AbstractDayPeriodCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDDAYPERIOD cd;

   public CDDAYPERIOD getCd() {
      return this.cd;
   }

   public void setCd(CDDAYPERIOD value) {
      this.cd = value;
   }
}
