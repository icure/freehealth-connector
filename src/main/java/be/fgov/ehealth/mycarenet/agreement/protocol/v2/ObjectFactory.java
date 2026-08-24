package be.fgov.ehealth.mycarenet.agreement.protocol.v2;

import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final String NS = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2";
   private static final QName _AskAgreementRequest_QNAME = new QName(NS, "AskAgreementRequest");
   private static final QName _AskAgreementResponse_QNAME = new QName(NS, "AskAgreementResponse");
   private static final QName _ConsultAgreementRequest_QNAME = new QName(NS, "ConsultAgreementRequest");
   private static final QName _ConsultAgreementResponse_QNAME = new QName(NS, "ConsultAgreementResponse");

   public ObjectFactory() {
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2",
      name = "AskAgreementRequest"
   )
   public JAXBElement<AskAgreementRequest> createAskAgreementRequest(SendRequestType value) {
      return new JAXBElement(_AskAgreementRequest_QNAME, AskAgreementRequest.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2",
      name = "AskAgreementResponse"
   )
   public JAXBElement<SendResponseType> createAskAgreementResponse(SendResponseType value) {
      return new JAXBElement(_AskAgreementResponse_QNAME, SendResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2",
      name = "ConsultAgreementRequest"
   )
   public JAXBElement<ConsultAgreementRequest> createConsultAgreementRequest(SendRequestType value) {
      return new JAXBElement(_ConsultAgreementRequest_QNAME, ConsultAgreementRequest.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2",
      name = "ConsultAgreementResponse"
   )
   public JAXBElement<SendResponseType> createConsultAgreementResponse(SendResponseType value) {
      return new JAXBElement(_ConsultAgreementResponse_QNAME, SendResponseType.class, (Class)null, value);
   }
}
