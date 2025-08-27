package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CbssPersonIdentificationsResponseType",
   propOrder = {"personIdentifications"}
)
public class CbssPersonIdentificationsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PersonIdentification"
   )
   protected List<PersonIdentificationType> personIdentifications;

   public List<PersonIdentificationType> getPersonIdentifications() {
      if (this.personIdentifications == null) {
         this.personIdentifications = new ArrayList();
      }

      return this.personIdentifications;
   }
}
