package be.gfddpp.services.systemservices.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "service",
   propOrder = {"serviceName", "uri"}
)
public class Service {
   @XmlElement(
      required = true
   )
   protected String serviceName;
   @XmlElement(
      name = "URI",
      required = true
   )
   protected String uri;

   public String getServiceName() {
      return this.serviceName;
   }

   public void setServiceName(String value) {
      this.serviceName = value;
   }

   public String getURI() {
      return this.uri;
   }

   public void setURI(String value) {
      this.uri = value;
   }
}
