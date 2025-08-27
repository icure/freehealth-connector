package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementCriteriaType",
   propOrder = {"reimbCriteriaCv", "name"}
)
public class ReimbursementCriteriaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReimbCriteriaCv",
      required = true
   )
   protected String reimbCriteriaCv;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;

   public String getReimbCriteriaCv() {
      return this.reimbCriteriaCv;
   }

   public void setReimbCriteriaCv(String value) {
      this.reimbCriteriaCv = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
