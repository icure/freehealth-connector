package be.fgov.ehealth.dics.protocol.v3;

import be.fgov.ehealth.dics.core.v3.refdata.SubstanceKeyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultStandardSubstanceAndSubstanceType",
   propOrder = {"substances"}
)
public class ConsultStandardSubstanceAndSubstanceType extends ConsultStandardSubstanceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Substance"
   )
   protected List<SubstanceKeyType> substances;

   public List<SubstanceKeyType> getSubstances() {
      if (this.substances == null) {
         this.substances = new ArrayList();
      }

      return this.substances;
   }
}
