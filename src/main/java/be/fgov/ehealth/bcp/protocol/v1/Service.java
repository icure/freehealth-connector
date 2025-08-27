package be.fgov.ehealth.bcp.protocol.v1;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "Service",
   namespace = "urn:be:fgov:ehealth:bcp:protocol:v1"
)
public class Service extends ServiceType {
}
