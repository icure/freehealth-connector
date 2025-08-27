package be.fgov.ehealth.mycarenet.commons.core.v3;

import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;
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
   name = "RoutingType",
   propOrder = {"careReceiver", "referenceDate", "period"}
)
public class RoutingType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareReceiver",
      required = true
   )
   protected CareReceiverIdType careReceiver;
   @XmlElement(
      name = "ReferenceDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime referenceDate;
   @XmlElement(
      name = "Period"
   )
   protected PeriodType period;

   public CareReceiverIdType getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverIdType value) {
      this.careReceiver = value;
   }

   public DateTime getReferenceDate() {
      return this.referenceDate;
   }

   public void setReferenceDate(DateTime value) {
      this.referenceDate = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }
}
