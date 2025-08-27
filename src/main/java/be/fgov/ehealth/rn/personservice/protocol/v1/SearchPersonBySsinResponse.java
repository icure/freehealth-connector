package be.fgov.ehealth.rn.personservice.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.rn.commons.business.v1.SsinWithCanceledAndReplacesStatusType;
import be.fgov.ehealth.rn.personservice.core.v1.PersonResponseResultType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchPersonBySsinResponseType",
   propOrder = {"ssin", "result"}
)
@XmlRootElement(
   name = "SearchPersonBySsinResponse"
)
public class SearchPersonBySsinResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin"
   )
   protected SsinWithCanceledAndReplacesStatusType ssin;
   @XmlElement(
      name = "Result"
   )
   protected PersonResponseResultType result;

   public SsinWithCanceledAndReplacesStatusType getSsin() {
      return this.ssin;
   }

   public void setSsin(SsinWithCanceledAndReplacesStatusType value) {
      this.ssin = value;
   }

   public PersonResponseResultType getResult() {
      return this.result;
   }

   public void setResult(PersonResponseResultType value) {
      this.result = value;
   }
}
