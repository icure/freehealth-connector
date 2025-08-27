package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.reimbursementlaw.submit.LegalReferenceKeyType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultLegalReferenceTraceType"
)
public class ConsultLegalReferenceTraceType extends LegalReferenceKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
}
