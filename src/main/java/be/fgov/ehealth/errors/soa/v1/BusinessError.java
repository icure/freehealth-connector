package be.fgov.ehealth.errors.soa.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BusinessErrorType"
)
@XmlRootElement(
   name = "BusinessError"
)
public class BusinessError extends SOAErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
}
