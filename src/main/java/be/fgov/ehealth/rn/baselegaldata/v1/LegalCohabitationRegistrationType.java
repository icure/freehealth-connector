package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LegalCohabitationRegistrationType",
   propOrder = {"registrationDate", "location", "registrationBailiff"}
)
public class LegalCohabitationRegistrationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RegistrationDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime registrationDate;
   @XmlElement(
      name = "Location"
   )
   protected LocationType location;
   @XmlElement(
      name = "RegistrationBailiff"
   )
   protected RegistrationBailiffType registrationBailiff;

   public DateTime getRegistrationDate() {
      return this.registrationDate;
   }

   public void setRegistrationDate(DateTime value) {
      this.registrationDate = value;
   }

   public LocationType getLocation() {
      return this.location;
   }

   public void setLocation(LocationType value) {
      this.location = value;
   }

   public RegistrationBailiffType getRegistrationBailiff() {
      return this.registrationBailiff;
   }

   public void setRegistrationBailiff(RegistrationBailiffType value) {
      this.registrationBailiff = value;
   }
}
