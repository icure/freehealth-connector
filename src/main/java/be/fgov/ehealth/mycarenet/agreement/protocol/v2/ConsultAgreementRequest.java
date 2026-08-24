package be.fgov.ehealth.mycarenet.agreement.protocol.v2;

import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "ConsultAgreementRequest",
   namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2"
)
public class ConsultAgreementRequest extends SendRequestType {
   private static final long serialVersionUID = 1L;
}
