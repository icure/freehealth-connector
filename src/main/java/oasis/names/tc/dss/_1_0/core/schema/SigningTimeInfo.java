package oasis.names.tc.dss._1_0.core.schema;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
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
   name = "SigningTimeInfoType",
   propOrder = {"signingTime", "signingTimeBoundaries"}
)
@XmlRootElement(
   name = "SigningTimeInfo"
)
public class SigningTimeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SigningTime",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime signingTime;
   @XmlElement(
      name = "SigningTimeBoundaries"
   )
   protected SigningTimeBoundaries signingTimeBoundaries;

   public DateTime getSigningTime() {
      return this.signingTime;
   }

   public void setSigningTime(DateTime value) {
      this.signingTime = value;
   }

   public SigningTimeBoundaries getSigningTimeBoundaries() {
      return this.signingTimeBoundaries;
   }

   public void setSigningTimeBoundaries(SigningTimeBoundaries value) {
      this.signingTimeBoundaries = value;
   }
}
