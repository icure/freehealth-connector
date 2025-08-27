package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FamilyCompositionHistoryType",
   propOrder = {"familyComposition"}
)
public class FamilyCompositionHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FamilyComposition",
      required = true
   )
   protected FamilyHistoryCompositionType familyComposition;

   public FamilyHistoryCompositionType getFamilyComposition() {
      return this.familyComposition;
   }

   public void setFamilyComposition(FamilyHistoryCompositionType value) {
      this.familyComposition = value;
   }
}
