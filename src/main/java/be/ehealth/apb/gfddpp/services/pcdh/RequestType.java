package be.ehealth.apb.gfddpp.services.pcdh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   namespace = "urn:be:fgov:ehealth:commons:protocol:v1"
)
@XmlSeeAlso({RoutedSealedRequestType.class, UploadPerformanceMetricRequestType.class, SealedMessageRequestType.class, SealedRequestType.class, CheckAliveRequestType.class})
public class RequestType {
}
