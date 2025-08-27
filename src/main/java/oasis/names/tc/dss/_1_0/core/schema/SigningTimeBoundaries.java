package oasis.names.tc.dss._1_0.core.schema;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
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
   propOrder = {"lowerBoundary", "upperBoundary"}
)
public class SigningTimeBoundaries implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LowerBoundary",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime lowerBoundary;
   @XmlElement(
      name = "UpperBoundary",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime upperBoundary;

   public DateTime getLowerBoundary() {
      return this.lowerBoundary;
   }

   public void setLowerBoundary(DateTime value) {
      this.lowerBoundary = value;
   }

   public DateTime getUpperBoundary() {
      return this.upperBoundary;
   }

   public void setUpperBoundary(DateTime value) {
      this.upperBoundary = value;
   }
}
