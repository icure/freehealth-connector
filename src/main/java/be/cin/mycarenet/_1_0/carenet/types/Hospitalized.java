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
   name = "HospitalizedType",
   propOrder = {"hospital", "admissionDate", "admissionService"}
)
@XmlRootElement(
   name = "Hospitalized"
)
public class Hospitalized implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Hospital",
      required = true
   )
   protected String hospital;
   @XmlElement(
      name = "AdmissionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime admissionDate;
   @XmlElement(
      name = "AdmissionService"
   )
   protected String admissionService;

   public String getHospital() {
      return this.hospital;
   }

   public void setHospital(String value) {
      this.hospital = value;
   }

   public DateTime getAdmissionDate() {
      return this.admissionDate;
   }

   public void setAdmissionDate(DateTime value) {
      this.admissionDate = value;
   }

   public String getAdmissionService() {
      return this.admissionService;
   }

   public void setAdmissionService(String value) {
      this.admissionService = value;
   }
}
