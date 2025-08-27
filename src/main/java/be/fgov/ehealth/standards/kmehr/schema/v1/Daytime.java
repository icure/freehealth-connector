package be.fgov.ehealth.standards.kmehr.schema.v1;

import org.taktik.connector.technical.adapter.XmlTimeNoTzAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"dayperiod", "time"}
)
public class Daytime implements Serializable {
   private static final long serialVersionUID = 1L;
   protected DayperiodType dayperiod;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlTimeNoTzAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;

   public DayperiodType getDayperiod() {
      return this.dayperiod;
   }

   public void setDayperiod(DayperiodType value) {
      this.dayperiod = value;
   }

   public DateTime getTime() {
      return this.time;
   }

   public void setTime(DateTime value) {
      this.time = value;
   }
}
