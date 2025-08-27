package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionalAuthorizationType",
   propOrder = {"profAuId", "purchasingAdvisorName", "professionalCode"}
)
public class ProfessionalAuthorizationType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ProfAuId"
   )
   protected long profAuId;
   @XmlElement(
      name = "PurchasingAdvisorName"
   )
   protected String purchasingAdvisorName;
   @XmlElement(
      name = "ProfessionalCode"
   )
   protected ProfessionalCodeType professionalCode;

   public long getProfAuId() {
      return this.profAuId;
   }

   public void setProfAuId(long value) {
      this.profAuId = value;
   }

   public String getPurchasingAdvisorName() {
      return this.purchasingAdvisorName;
   }

   public void setPurchasingAdvisorName(String value) {
      this.purchasingAdvisorName = value;
   }

   public ProfessionalCodeType getProfessionalCode() {
      return this.professionalCode;
   }

   public void setProfessionalCode(ProfessionalCodeType value) {
      this.professionalCode = value;
   }
}
