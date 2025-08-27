package be.fgov.ehealth.hubservices.core.v2;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BasicConsentType",
   propOrder = {"cds"}
)
public class BasicConsentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "cd"
   )
   protected List<CDCONSENT> cds;

   public List<CDCONSENT> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }
}
