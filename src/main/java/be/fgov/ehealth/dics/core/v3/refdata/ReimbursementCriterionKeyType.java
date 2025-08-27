package be.fgov.ehealth.dics.core.v3.refdata;

import be.fgov.ehealth.dics.protocol.v3.ReimbursementCriterionType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementCriterionKeyType"
)
@XmlSeeAlso({ReimbursementCriterionType.class})
public class ReimbursementCriterionKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Category",
      required = true
   )
   protected String category;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public String getCategory() {
      return this.category;
   }

   public void setCategory(String value) {
      this.category = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
