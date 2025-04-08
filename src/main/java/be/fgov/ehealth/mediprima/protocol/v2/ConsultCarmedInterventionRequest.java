package be.fgov.ehealth.mediprima.protocol.v2;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(
  name= "ConsultCarmedInterventionRequest",
  namespace= "urn:be:fgov:ehealth:mediprima:protocol:v2"
)
public class ConsultCarmedInterventionRequest extends RequestType implements Serializable {
  private static final long serialVersionUID = 1L;
}
