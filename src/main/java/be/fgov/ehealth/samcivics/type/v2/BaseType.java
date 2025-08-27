package be.fgov.ehealth.samcivics.type.v2;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BaseType",
   propOrder = {"startDate", "endDate"}
)
@XmlSeeAlso({ProductType.class, ProfessionalAuthorizationType.class, AddedDocumentType.class, FindParagraphType.class, FindAmppType.class, AmppType.class, AtmType.class, CopaymentType.class, ExclusionType.class, ParagraphType.class, PriceType.class, ProfessionalCodeType.class, ReimbursementType.class, TherapyType.class, VerseType.class})
public class BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "StartDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "EndDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
