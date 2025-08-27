package be.fgov.ehealth.genericinsurability.core.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
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
   name = "PeriodType",
   propOrder = {"periodStart", "periodEnd"}
)
public class PeriodType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PeriodStart",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodStart;
   @XmlElement(
      name = "PeriodEnd",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime periodEnd;

   public DateTime getPeriodStart() {
      return this.periodStart;
   }

   public void setPeriodStart(DateTime value) {
      this.periodStart = value;
   }

   public DateTime getPeriodEnd() {
      return this.periodEnd;
   }

   public void setPeriodEnd(DateTime value) {
      this.periodEnd = value;
   }
}
