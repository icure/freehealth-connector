package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import be.fgov.ehealth.rn.commons.business.v1.LocalizedDescriptionType;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStateInfoBaseType",
   propOrder = {"civilStateCode", "civilStateDescriptions", "partner", "location", "judgment", "transcription", "inceptionDate"}
)
@XmlSeeAlso({CivilStateInfoWithSourceType.class})
public class CivilStateInfoBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilStateCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer civilStateCode;
   @XmlElement(
      name = "CivilStateDescription"
   )
   protected List<LocalizedDescriptionType> civilStateDescriptions;
   @XmlElement(
      name = "Partner"
   )
   protected PartnerType partner;
   @XmlElement(
      name = "Location"
   )
   protected LocationType location;
   @XmlElement(
      name = "Judgment"
   )
   protected JudgmentType judgment;
   @XmlElement(
      name = "Transcription"
   )
   protected TranscriptionType transcription;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public Integer getCivilStateCode() {
      return this.civilStateCode;
   }

   public void setCivilStateCode(Integer value) {
      this.civilStateCode = value;
   }

   public List<LocalizedDescriptionType>  getCivilStateDescriptions() {
      if (this.civilStateDescriptions == null) {
         this.civilStateDescriptions = new ArrayList();
      }

      return this.civilStateDescriptions;
   }

   public PartnerType getPartner() {
      return this.partner;
   }

   public void setPartner(PartnerType value) {
      this.partner = value;
   }

   public LocationType getLocation() {
      return this.location;
   }

   public void setLocation(LocationType value) {
      this.location = value;
   }

   public JudgmentType getJudgment() {
      return this.judgment;
   }

   public void setJudgment(JudgmentType value) {
      this.judgment = value;
   }

   public TranscriptionType getTranscription() {
      return this.transcription;
   }

   public void setTranscription(TranscriptionType value) {
      this.transcription = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
