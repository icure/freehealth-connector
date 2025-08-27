package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDDRUGPRESENTATION;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "presentationType",
   propOrder = {"cd"}
)
public class PresentationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDDRUGPRESENTATION cd;

   public CDDRUGPRESENTATION getCd() {
      return this.cd;
   }

   public void setCd(CDDRUGPRESENTATION value) {
      this.cd = value;
   }
}
