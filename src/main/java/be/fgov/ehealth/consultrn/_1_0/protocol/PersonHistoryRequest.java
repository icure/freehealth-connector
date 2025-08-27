package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonHistoryRequest",
   propOrder = {"ssin"}
)
@XmlSeeAlso({PersonHistoryFamilyCompositionRequest.class, PersonHistoryNationalityRequest.class, PersonHistoryAddressRequest.class, PersonHistoryDeceaseRequest.class, PersonHistoryCivilStateRequest.class, PersonHistoryBirthRequest.class, PersonHistoryGenderRequest.class, PersonHistoryNameRequest.class})
public class PersonHistoryRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected String ssin;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }
}
