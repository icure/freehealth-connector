package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MonitoringRequestType"
)
@XmlRootElement(
   name = "MonitoringRequest"
)
public class MonitoringRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
