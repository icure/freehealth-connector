package be.fgov.ehealth.mycarenet.attest.protocol.v3;

import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _SendAttestationRequest_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v3", "SendAttestationRequest");
   private static final QName _SendAttestationResponse_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v3", "SendAttestationResponse");
   private static final QName _CancelAttestationRequest_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v3", "CancelAttestationRequest");
   private static final QName _CancelAttestationResponse_QNAME = new QName("urn:be:fgov:ehealth:mycarenet:attest:protocol:v3", "CancelAttestationResponse");

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3",
      name = "SendAttestationRequest"
   )
   public JAXBElement<SendRequestType> createSendAttestationRequest(SendRequestType value) {
      return new JAXBElement(_SendAttestationRequest_QNAME, SendRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3",
      name = "SendAttestationResponse"
   )
   public JAXBElement<SendResponseType> createSendAttestationResponse(SendResponseType value) {
      return new JAXBElement(_SendAttestationResponse_QNAME, SendResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3",
      name = "CancelAttestationRequest"
   )
   public JAXBElement<SendRequestType> createCancelAttestationRequest(SendRequestType value) {
      return new JAXBElement(_CancelAttestationRequest_QNAME, SendRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3",
      name = "CancelAttestationResponse"
   )
   public JAXBElement<SendResponseType> createCancelAttestationResponse(SendResponseType value) {
      return new JAXBElement(_CancelAttestationResponse_QNAME, SendResponseType.class, (Class)null, value);
   }
}
