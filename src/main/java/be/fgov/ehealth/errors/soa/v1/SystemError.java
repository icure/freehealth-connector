package be.fgov.ehealth.errors.soa.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SystemErrorType"
)
@XmlRootElement(
   name = "SystemError"
)
public class SystemError extends SOAErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
}
