package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapeuticExclusionWithOperationContext",
   propOrder = {"operationcontexts"}
)
public class TherapeuticExclusionWithOperationContext extends TherapeuticExclusionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "operationcontext"
   )
   protected List<OperationContextType> operationcontexts;

   public List<OperationContextType> getOperationcontexts() {
      if (this.operationcontexts == null) {
         this.operationcontexts = new ArrayList();
      }

      return this.operationcontexts;
   }
}
