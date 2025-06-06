package be.fgov.ehealth.mediprima.protocol.v2;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.soap.SOAPMessage;

@XmlRootElement(
        name = "ConsultCarmedInterventionResponse"
)
public class ConsultCarmedInterventionResponse extends ResponseType implements SoapConversationLogger {
    private static final long serialVersionUID = 1L;

    @XmlTransient
    private SOAPMessage soapRequest;
    @XmlTransient private SOAPMessage soapResponse;
    @XmlTransient
    private Integer upstreamTiming;
    @XmlTransient
    private ConsultCarmedInterventionResponseType response;
    private MycarenetConversation mycarenetConversation;


    @Override
    public Integer getUpstreamTiming() {
        return 0;
    }

    @Override
    public void setUpstreamTiming(Integer timing) {}

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

    public ConsultCarmedInterventionResponseType getResponse() {
        return response;
    }

    public void setResponse(ConsultCarmedInterventionResponseType response) {
        this.response = response;
    }

    public MycarenetConversation getMycarenetConversation() {
        return mycarenetConversation;
    }

    public void setMycarenetConversation(MycarenetConversation mycarenetConversation) {
        this.mycarenetConversation = mycarenetConversation;
    }
}
