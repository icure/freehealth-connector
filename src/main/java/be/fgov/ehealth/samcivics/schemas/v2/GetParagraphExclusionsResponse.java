package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.ExcludedParagraphType;
import be.fgov.ehealth.samcivics.type.v2.UnstructuredExclusionRuleType;
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
   name = "GetParagraphExclusionsResponseType",
   propOrder = {"excludedParagraphs", "unstructuredExclusionRules"}
)
@XmlRootElement(
   name = "GetParagraphExclusionsResponse"
)
public class GetParagraphExclusionsResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ExcludedParagraph"
   )
   protected List<ExcludedParagraphType> excludedParagraphs;
   @XmlElement(
      name = "UnstructuredExclusionRule"
   )
   protected List<UnstructuredExclusionRuleType> unstructuredExclusionRules;

   public List<ExcludedParagraphType> getExcludedParagraphs() {
      if (this.excludedParagraphs == null) {
         this.excludedParagraphs = new ArrayList();
      }

      return this.excludedParagraphs;
   }

   public List<UnstructuredExclusionRuleType> getUnstructuredExclusionRules() {
      if (this.unstructuredExclusionRules == null) {
         this.unstructuredExclusionRules = new ArrayList();
      }

      return this.unstructuredExclusionRules;
   }
}
