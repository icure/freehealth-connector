package be.fgov.ehealth.etee.commons.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EteeStatusDetailType",
   propOrder = {"errors"}
)
@XmlRootElement(
   name = "EteeStatusDetail"
)
public class EteeStatusDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Errors"
   )
   protected List<EteeErrorType> errors;

   public List<EteeErrorType> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }
}
