package be.fgov.ehealth.hubservices.core.v2;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import org.taktik.connector.technical.adapter.XmlTimeAdapter;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"id", "author", "date", "time", "maxrows"}
)
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected IDKMEHR id;
   @XmlElement(
      required = true
   )
   protected AuthorWithPatientAndPersonType author;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlTimeAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;
   protected BigDecimal maxrows;

   public IDKMEHR getId() {
      return this.id;
   }

   public void setId(IDKMEHR value) {
      this.id = value;
   }

   public AuthorWithPatientAndPersonType getAuthor() {
      return this.author;
   }

   public void setAuthor(AuthorWithPatientAndPersonType value) {
      this.author = value;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public DateTime getTime() {
      return this.time;
   }

   public void setTime(DateTime value) {
      this.time = value;
   }

   public BigDecimal getMaxrows() {
      return this.maxrows;
   }

   public void setMaxrows(BigDecimal value) {
      this.maxrows = value;
   }
}
