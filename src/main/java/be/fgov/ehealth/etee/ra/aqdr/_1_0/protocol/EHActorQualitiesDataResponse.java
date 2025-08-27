package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"ssin", "authorization", "legalPerson", "naturalPerson"}
)
@XmlRootElement(
   name = "EHActorQualitiesDataResponse"
)
public class EHActorQualitiesDataResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected long ssin;
   @XmlElement(
      name = "Authorization"
   )
   protected boolean authorization;
   @XmlElement(
      name = "LegalPerson"
   )
   protected LegalPerson legalPerson;
   @XmlElement(
      name = "NaturalPerson"
   )
   protected NaturalPerson naturalPerson;

   public long getSSIN() {
      return this.ssin;
   }

   public void setSSIN(long value) {
      this.ssin = value;
   }

   public boolean isAuthorization() {
      return this.authorization;
   }

   public void setAuthorization(boolean value) {
      this.authorization = value;
   }

   public LegalPerson getLegalPerson() {
      return this.legalPerson;
   }

   public void setLegalPerson(LegalPerson value) {
      this.legalPerson = value;
   }

   public NaturalPerson getNaturalPerson() {
      return this.naturalPerson;
   }

   public void setNaturalPerson(NaturalPerson value) {
      this.naturalPerson = value;
   }
}
