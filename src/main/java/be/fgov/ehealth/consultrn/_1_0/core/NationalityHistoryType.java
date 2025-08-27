package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityHistoryType",
   propOrder = {"nationality", "obtainementReason", "registrationLocation"}
)
public class NationalityHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nationality",
      required = true
   )
   protected NationalityType nationality;
   @XmlElement(
      name = "ObtainementReason"
   )
   protected ObtainementReasonType obtainementReason;
   @XmlElement(
      name = "RegistrationLocation"
   )
   protected WhereType registrationLocation;

   public NationalityType getNationality() {
      return this.nationality;
   }

   public void setNationality(NationalityType value) {
      this.nationality = value;
   }

   public ObtainementReasonType getObtainementReason() {
      return this.obtainementReason;
   }

   public void setObtainementReason(ObtainementReasonType value) {
      this.obtainementReason = value;
   }

   public WhereType getRegistrationLocation() {
      return this.registrationLocation;
   }

   public void setRegistrationLocation(WhereType value) {
      this.registrationLocation = value;
   }
}
