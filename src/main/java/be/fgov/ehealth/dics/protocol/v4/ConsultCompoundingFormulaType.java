package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.compounding.common.CompoundingFormulaKeyType;
import be.fgov.ehealth.dics.core.v4.compounding.common.SynonymType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCompoundingFormulaType",
   propOrder = {"synonyms"}
)
public class ConsultCompoundingFormulaType extends CompoundingFormulaKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Synonym",
      required = true
   )
   protected List<SynonymType> synonyms;

   public List<SynonymType> getSynonyms() {
      if (this.synonyms == null) {
         this.synonyms = new ArrayList();
      }

      return this.synonyms;
   }
}
