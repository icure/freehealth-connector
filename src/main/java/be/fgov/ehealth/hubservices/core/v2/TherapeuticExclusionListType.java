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
   name = "TherapeuticExclusionListType",
   propOrder = {"therapeuticexclusions"}
)
public class TherapeuticExclusionListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "therapeuticexclusion"
   )
   protected List<TherapeuticExclusionWithOperationContext> therapeuticexclusions;

   public List<TherapeuticExclusionWithOperationContext> getTherapeuticexclusions() {
      if (this.therapeuticexclusions == null) {
         this.therapeuticexclusions = new ArrayList();
      }

      return this.therapeuticexclusions;
   }
}
