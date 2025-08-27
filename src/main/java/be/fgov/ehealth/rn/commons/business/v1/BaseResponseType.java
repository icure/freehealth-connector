package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BaseResponseType",
   propOrder = {"informationCustomer", "informationCBSS", "legalContext"}
)
public abstract class BaseResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InformationCustomer",
      required = true
   )
   protected InformationCustomerType informationCustomer;
   @XmlElement(
      name = "InformationCBSS",
      required = true
   )
   protected InformationCBSSType informationCBSS;
   @XmlElement(
      name = "LegalContext",
      required = true
   )
   protected String legalContext;

   public InformationCustomerType getInformationCustomer() {
      return this.informationCustomer;
   }

   public void setInformationCustomer(InformationCustomerType value) {
      this.informationCustomer = value;
   }

   public InformationCBSSType getInformationCBSS() {
      return this.informationCBSS;
   }

   public void setInformationCBSS(InformationCBSSType value) {
      this.informationCBSS = value;
   }

   public String getLegalContext() {
      return this.legalContext;
   }

   public void setLegalContext(String value) {
      this.legalContext = value;
   }
}
