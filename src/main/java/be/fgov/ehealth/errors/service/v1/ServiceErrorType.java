package be.fgov.ehealth.errors.service.v1;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.errors.core.v1.ErrorType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ServiceErrorType",
   propOrder = {"actor", "dateTime", "trace"}
)
@XmlSeeAlso({BusinessError.class, SystemError.class})
public class ServiceErrorType extends ErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Actor",
      required = true
   )
   protected String actor;
   @XmlElement(
      name = "DateTime",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime dateTime;
   @XmlElement(
      name = "Trace"
   )
   protected TraceType trace;

   public String getActor() {
      return this.actor;
   }

   public void setActor(String value) {
      this.actor = value;
   }

   public DateTime getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(DateTime value) {
      this.dateTime = value;
   }

   public TraceType getTrace() {
      return this.trace;
   }

   public void setTrace(TraceType value) {
      this.trace = value;
   }
}
