package be.fgov.ehealth.monitoring.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.monitoring.core.v2.MonitoringXML;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AliveCheckResponseType",
   propOrder = {"monitoringXML"}
)
@XmlRootElement(
   name = "AliveCheckResponse"
)
public class AliveCheckResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MonitoringXML",
      required = true
   )
   protected MonitoringXML monitoringXML;

   public MonitoringXML getMonitoringXML() {
      return this.monitoringXML;
   }

   public void setMonitoringXML(MonitoringXML value) {
      this.monitoringXML = value;
   }
}
