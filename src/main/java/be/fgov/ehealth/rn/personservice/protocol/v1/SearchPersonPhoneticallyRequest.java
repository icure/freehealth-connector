package be.fgov.ehealth.rn.personservice.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.rn.personservice.core.v1.SearchPersonPhoneticallyCriteriaType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchPersonPhoneticallyRequestType",
   propOrder = {"applicationId", "criteria"}
)
@XmlRootElement(
   name = "SearchPersonPhoneticallyRequest"
)
public class SearchPersonPhoneticallyRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationId",
      required = true
   )
   protected String applicationId;
   @XmlElement(
      name = "Criteria",
      required = true
   )
   protected SearchPersonPhoneticallyCriteriaType criteria;

   public String getApplicationId() {
      return this.applicationId;
   }

   public void setApplicationId(String value) {
      this.applicationId = value;
   }

   public SearchPersonPhoneticallyCriteriaType getCriteria() {
      return this.criteria;
   }

   public void setCriteria(SearchPersonPhoneticallyCriteriaType value) {
      this.criteria = value;
   }
}
