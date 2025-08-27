package be.fgov.ehealth.hubservices.core.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTHERAPEUTICLINK;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapeuticLinkType",
   propOrder = {"patient", "hcparty", "cd", "startdate", "enddate", "comment"}
)
@XmlRootElement(
   name = "TherapeuticLinkType"
)
public class TherapeuticLinkType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   @XmlElement(
      required = true
   )
   protected HCPartyIdType hcparty;
   @XmlElement(
      required = true
   )
   protected CDTHERAPEUTICLINK cd;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startdate;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime enddate;
   protected String comment;

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public HCPartyIdType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HCPartyIdType value) {
      this.hcparty = value;
   }

   public CDTHERAPEUTICLINK getCd() {
      return this.cd;
   }

   public void setCd(CDTHERAPEUTICLINK value) {
      this.cd = value;
   }

   public DateTime getStartdate() {
      return this.startdate;
   }

   public void setStartdate(DateTime value) {
      this.startdate = value;
   }

   public DateTime getEnddate() {
      return this.enddate;
   }

   public void setEnddate(DateTime value) {
      this.enddate = value;
   }

   public String getComment() {
      return this.comment;
   }

   public void setComment(String value) {
      this.comment = value;
   }
}
