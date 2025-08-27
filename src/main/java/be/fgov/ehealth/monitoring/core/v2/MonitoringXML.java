package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MonitoringResponseType",
   propOrder = {"monitoring"}
)
@XmlRootElement(
   name = "MonitoringXML",
   namespace = ""
)
public class MonitoringXML implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Monitoring",
      required = true
   )
   protected MonitoringType monitoring;

   public MonitoringType getMonitoring() {
      return this.monitoring;
   }

   public void setMonitoring(MonitoringType value) {
      this.monitoring = value;
   }
}
