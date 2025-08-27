package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendRequestMemberDataType"
)
@XmlRootElement(
   name = "MemberDataConsultationRequest"
)
public class MemberDataConsultationRequest extends SendRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
