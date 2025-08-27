package be.cin.mycarenet._1_0.carenet.types;

import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NurseContractualCareUpdateDetailType",
   propOrder = {"consultantDoctor", "provider", "visitDate", "newValue", "paliatifPatient"}
)
@XmlRootElement(
   name = "NurseContractualCareUpdateDetail"
)
public class NurseContractualCareUpdateDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ConsultantDoctor",
      required = true
   )
   protected String consultantDoctor;
   @XmlElement(
      name = "Provider",
      required = true
   )
   protected String provider;
   @XmlElement(
      name = "VisitDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime visitDate;
   @XmlElement(
      required = true
   )
   protected NurseContractualCareDetailType newValue;
   @XmlElement(
      name = "PaliatifPatient"
   )
   protected Boolean paliatifPatient;
   @XmlAttribute(
      name = "type",
      required = true
   )
   protected MessageNatureType type;

   public String getConsultantDoctor() {
      return this.consultantDoctor;
   }

   public void setConsultantDoctor(String value) {
      this.consultantDoctor = value;
   }

   public String getProvider() {
      return this.provider;
   }

   public void setProvider(String value) {
      this.provider = value;
   }

   public DateTime getVisitDate() {
      return this.visitDate;
   }

   public void setVisitDate(DateTime value) {
      this.visitDate = value;
   }

   public NurseContractualCareDetailType getNewValue() {
      return this.newValue;
   }

   public void setNewValue(NurseContractualCareDetailType value) {
      this.newValue = value;
   }

   public Boolean isPaliatifPatient() {
      return this.paliatifPatient;
   }

   public void setPaliatifPatient(Boolean value) {
      this.paliatifPatient = value;
   }

   public MessageNatureType getType() {
      return this.type;
   }

   public void setType(MessageNatureType value) {
      this.type = value;
   }
}
