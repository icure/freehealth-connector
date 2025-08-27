package be.fgov.ehealth.etkdepot._1_0.protocol;

import be.fgov.ehealth.commons._1_0.protocol.RequestType;
import java.io.Serializable;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"searchCriteria"}
)
@XmlRootElement(
   name = "GetEtkRequest"
)
public class GetEtkRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SearchCriteria",
      required = true
   )
   protected SearchCriteriaType searchCriteria;

   public SearchCriteriaType getSearchCriteria() {
      return this.searchCriteria;
   }

   public void setSearchCriteria(SearchCriteriaType value) {
      this.searchCriteria = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      GetEtkRequest that = (GetEtkRequest) o;
      return Objects.equals(searchCriteria, that.searchCriteria);
   }

   @Override
   public int hashCode() {
      return Objects.hash(searchCriteria);
   }
}
