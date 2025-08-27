package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDADMINISTRATIONUNIT;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "administrationunitType",
   propOrder = {"cd"}
)
public class AdministrationunitType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDADMINISTRATIONUNIT cd;

   public CDADMINISTRATIONUNIT getCd() {
      return this.cd;
   }

   public void setCd(CDADMINISTRATIONUNIT value) {
      this.cd = value;
   }
}
