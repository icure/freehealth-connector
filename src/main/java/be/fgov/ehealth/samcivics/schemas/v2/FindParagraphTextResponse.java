package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.ParagraphAndChildrenType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindParagraphTextResponseType",
   propOrder = {"paragraph"}
)
@XmlRootElement(
   name = "FindParagraphTextResponse"
)
public class FindParagraphTextResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Paragraph",
      required = true
   )
   protected ParagraphAndChildrenType paragraph;

   public ParagraphAndChildrenType getParagraph() {
      return this.paragraph;
   }

   public void setParagraph(ParagraphAndChildrenType value) {
      this.paragraph = value;
   }
}
