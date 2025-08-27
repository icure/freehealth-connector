package be.fgov.ehealth.rn.personservice.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.rn.personservice.core.v1.PersonResponseResultsType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchPersonPhoneticallyResponseType",
   propOrder = {"result"}
)
@XmlRootElement(
   name = "SearchPersonPhoneticallyResponse"
)
public class SearchPersonPhoneticallyResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Result"
   )
   protected PersonResponseResultsType result;

   public PersonResponseResultsType getResult() {
      return this.result;
   }

   public void setResult(PersonResponseResultsType value) {
      this.result = value;
   }
}
