package be.fgov.ehealth.etkra.protocol.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "ActivateETKResponse"
)
public class ActivateETKResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
}
