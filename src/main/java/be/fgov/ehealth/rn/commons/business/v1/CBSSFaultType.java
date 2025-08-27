package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CBSSFaultType",
   propOrder = {"informationCustomer", "informationCBSS", "details"}
)
public class CBSSFaultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InformationCustomer"
   )
   protected InformationCustomerType informationCustomer;
   @XmlElement(
      name = "InformationCBSS",
      required = true
   )
   protected InformationCBSSType informationCBSS;
   @XmlElement(
      name = "Detail",
      required = true
   )
   protected List details;

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

   public List getDetails() {
      if (this.details == null) {
         this.details = new ArrayList();
      }

      return this.details;
   }
}
