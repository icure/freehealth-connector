package be.ehealth.apb.gfddpp.services.pcdh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "BusinessError",
   namespace = "urn:be:fgov:ehealth:errors:soa:v1"
)
public class BusinessError extends SOAErrorType {
}
