package be.fgov.ehealth.consultrn._1_0.protocol;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HeaderType",
   propOrder = {"applicationId", "date", "sequenceNumber", "environment"}
)
public class HeaderType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationId",
      required = true
   )
   protected String applicationId;
   @XmlElement(
      name = "Date",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      name = "SequenceNumber",
      required = true
   )
   protected BigInteger sequenceNumber;
   @XmlElement(
      name = "Environment",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected EnvironmentType environment;

   public String getApplicationId() {
      return this.applicationId;
   }

   public void setApplicationId(String value) {
      this.applicationId = value;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public BigInteger getSequenceNumber() {
      return this.sequenceNumber;
   }

   public void setSequenceNumber(BigInteger value) {
      this.sequenceNumber = value;
   }

   public EnvironmentType getEnvironment() {
      return this.environment;
   }

   public void setEnvironment(EnvironmentType value) {
      this.environment = value;
   }
}
