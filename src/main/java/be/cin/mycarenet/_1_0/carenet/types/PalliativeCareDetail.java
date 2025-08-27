package be.cin.mycarenet._1_0.carenet.types;

import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PalliativeCareDetailType",
   propOrder = {"nurseReference", "requester", "periodStart", "group", "usualDoctor", "careProvider"}
)
@XmlRootElement(
   name = "PalliativeCareDetail"
)
public class PalliativeCareDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NurseReference",
      required = true
   )
   protected String nurseReference;
   @XmlElement(
      name = "Requester",
      required = true
   )
   protected String requester;
   @XmlElement(
      name = "PeriodStart",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodStart;
   @XmlElement(
      name = "Group",
      required = true
   )
   protected PalliativeCareGroupType group;
   @XmlElement(
      name = "UsualDoctor",
      required = true
   )
   protected String usualDoctor;
   @XmlElement(
      name = "CareProvider",
      required = true
   )
   protected PalliativeCareCareProviderType careProvider;

   public String getNurseReference() {
      return this.nurseReference;
   }

   public void setNurseReference(String value) {
      this.nurseReference = value;
   }

   public String getRequester() {
      return this.requester;
   }

   public void setRequester(String value) {
      this.requester = value;
   }

   public DateTime getPeriodStart() {
      return this.periodStart;
   }

   public void setPeriodStart(DateTime value) {
      this.periodStart = value;
   }

   public PalliativeCareGroupType getGroup() {
      return this.group;
   }

   public void setGroup(PalliativeCareGroupType value) {
      this.group = value;
   }

   public String getUsualDoctor() {
      return this.usualDoctor;
   }

   public void setUsualDoctor(String value) {
      this.usualDoctor = value;
   }

   public PalliativeCareCareProviderType getCareProvider() {
      return this.careProvider;
   }

   public void setCareProvider(PalliativeCareCareProviderType value) {
      this.careProvider = value;
   }
}
