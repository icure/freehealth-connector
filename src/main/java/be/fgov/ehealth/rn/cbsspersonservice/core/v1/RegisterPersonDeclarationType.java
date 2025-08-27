package be.fgov.ehealth.rn.cbsspersonservice.core.v1;

import be.fgov.ehealth.rn.cbsspersonlegaldata.v1.CbssPersonRequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegisterPersonDeclarationType",
   propOrder = {"person"}
)
public class RegisterPersonDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Person",
      required = true
   )
   protected CbssPersonRequestType person;

   public CbssPersonRequestType getPerson() {
      return this.person;
   }

   public void setPerson(CbssPersonRequestType value) {
      this.person = value;
   }
}
