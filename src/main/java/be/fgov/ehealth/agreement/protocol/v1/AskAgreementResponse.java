package be.fgov.ehealth.agreement.protocol.v1;

import be.fgov.ehealth.commons.protocol.SoapConversationLogger;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.soap.SOAPMessage;

@XmlRootElement(
   name = "AskAgreementResponse",
   namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1"
)
public class AskAgreementResponse extends SendResponseType implements SoapConversationLogger {
   private static final long serialVersionUID = 1L;

   @XmlTransient
   private SOAPMessage soapRequest;
   @XmlTransient private SOAPMessage soapResponse;
   @XmlTransient
   private Integer upstreamTiming;

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

   public AskAgreementResponse() {
   }
}
