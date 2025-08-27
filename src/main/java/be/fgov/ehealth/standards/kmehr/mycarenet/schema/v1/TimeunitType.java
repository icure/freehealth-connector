package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDTIMEUNIT;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "timeunitType",
   propOrder = {"cd"}
)
public class TimeunitType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDTIMEUNIT cd;

   public CDTIMEUNIT getCd() {
      return this.cd;
   }

   public void setCd(CDTIMEUNIT value) {
      this.cd = value;
   }
}
