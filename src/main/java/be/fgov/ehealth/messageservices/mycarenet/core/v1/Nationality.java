package be.fgov.ehealth.messageservices.mycarenet.core.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDCOUNTRY;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"cd"}
)
public class Nationality implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDCOUNTRY cd;

   public CDCOUNTRY getCd() {
      return this.cd;
   }

   public void setCd(CDCOUNTRY value) {
      this.cd = value;
   }
}
