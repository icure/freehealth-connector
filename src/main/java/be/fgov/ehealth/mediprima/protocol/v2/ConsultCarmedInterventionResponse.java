package be.fgov.ehealth.mediprima.protocol.v2;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.SOAPMessage;
import java.io.Serializable;

@XmlRootElement(
  name = "ConsultCarmedInterventionResponse",
  namespace = "urn:be:fgov:ehealth:mediprima:protocol:v2"
)
public class ConsultCarmedInterventionResponse extends ResponseType implements SoapConversationLogger {
  private static final long serialVersionUID = 1L;

  @XmlTransient
  private SOAPMessage soapRequest;
  @XmlTransient private SOAPMessage soapResponse;
  @XmlTransient
  private Integer upstreamTiming;


  @Override
  public Integer getUpstreamTiming() {
    return 0;
  }

  @Override
  public void setUpstreamTiming(Integer timing) {

  }

  @Override
  public SOAPMessage getSoapRequest() {
    return null;
  }

  @Override
  public void setSoapRequest(SOAPMessage soapRequest) {

  }

  @Override
  public SOAPMessage getSoapResponse() {
    return null;
  }

  @Override
  public void setSoapResponse(SOAPMessage soapResponse) {

  }
}
