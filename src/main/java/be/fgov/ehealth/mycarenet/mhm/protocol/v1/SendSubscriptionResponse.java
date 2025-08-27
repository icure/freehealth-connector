package be.fgov.ehealth.mycarenet.mhm.protocol.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.soap.SOAPMessage;

@XmlRootElement(
		name = "SendSubscriptionResponse",
		namespace = "urn:be:fgov:ehealth:mycarenet:medicalhousemembership:protocol:v1"
)
public class SendSubscriptionResponse extends SendResponseType implements SoapConversationLogger {
	private static final long serialVersionUID = 1L;

	@XmlTransient
	Integer upstreamTiming;
	@XmlTransient
	private SOAPMessage soapRequest;
	@XmlTransient private SOAPMessage soapResponse;

	@Override
	public SOAPMessage getSoapRequest() {
		return soapRequest;
	}

	@Override
	public void setSoapRequest(SOAPMessage soapRequest) {
		this.soapRequest = soapRequest;
	}

	@Override
	public SOAPMessage getSoapResponse() {
		return soapResponse;
	}

	@Override
	public void setSoapResponse(SOAPMessage soapResponse) {
		this.soapResponse = soapResponse;
	}

	@Override
	public Integer getUpstreamTiming() {
		return upstreamTiming;
	}

	@Override
	public void setUpstreamTiming(Integer upstreamTiming) {
		this.upstreamTiming = upstreamTiming;
	}
}
