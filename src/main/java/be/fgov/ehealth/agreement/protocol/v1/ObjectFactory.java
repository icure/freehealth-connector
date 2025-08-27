package be.fgov.ehealth.agreement.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import jakarta.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AskAgreementRequest_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1", "AskAgreementRequest");
   private static final QName _AskAgreementResponse_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1", "AskAgreementResponse");
   private static final QName _ConsultAgreementRequest_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1", "ConsultAgreementRequest");
   private static final QName _ConsultAgreementResponse_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1", "ConsultAgreementResponse");

   public ObjectFactory() {
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1",
      name = "AskAgreementRequest"
   )
   public JAXBElement<AskAgreementRequest> createAskAgreementRequest(SendRequestType value) {
      return new JAXBElement(_AskAgreementRequest_QNAME, AskAgreementRequest.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1",
      name = "AskAgreementResponse"
   )
   public JAXBElement<SendResponseType> createAskAgreementResponse(SendResponseType value) {
      return new JAXBElement(_AskAgreementResponse_QNAME, SendResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1",
      name = "ConsultAgreementRequest"
   )
   public JAXBElement<ConsultAgreementRequest> createConsultAgreementRequest(SendRequestType value) {
      return new JAXBElement(_ConsultAgreementRequest_QNAME, ConsultAgreementRequest.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1",
      name = "ConsultAgreementResponse"
   )
   public JAXBElement<SendResponseType> createConsultAgreementResponse(SendResponseType value) {
      return new JAXBElement(_ConsultAgreementResponse_QNAME, SendResponseType.class, (Class)null, value);
   }
}
