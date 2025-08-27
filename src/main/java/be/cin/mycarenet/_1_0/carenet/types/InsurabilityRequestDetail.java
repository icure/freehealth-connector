package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsurabilityRequestDetailType",
   propOrder = {"insurabilityRequestType", "period", "insurabilityContactType", "insurabilityReference"}
)
@XmlRootElement(
   name = "InsurabilityRequestDetail"
)
public class InsurabilityRequestDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsurabilityRequestType",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected InsurabilityRequestTypeType insurabilityRequestType;
   @XmlElement(
      name = "Period",
      required = true
   )
   protected PeriodType period;
   @XmlElement(
      name = "InsurabilityContactType",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected InsurabilityContactTypeType insurabilityContactType;
   @XmlElement(
      name = "InsurabilityReference"
   )
   protected String insurabilityReference;

   public InsurabilityRequestTypeType getInsurabilityRequestType() {
      return this.insurabilityRequestType;
   }

   public void setInsurabilityRequestType(InsurabilityRequestTypeType value) {
      this.insurabilityRequestType = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }

   public InsurabilityContactTypeType getInsurabilityContactType() {
      return this.insurabilityContactType;
   }

   public void setInsurabilityContactType(InsurabilityContactTypeType value) {
      this.insurabilityContactType = value;
   }

   public String getInsurabilityReference() {
      return this.insurabilityReference;
   }

   public void setInsurabilityReference(String value) {
      this.insurabilityReference = value;
   }
}
