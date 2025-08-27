package be.fgov.ehealth.bcp.protocol.v1;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ServiceList createServiceList() {
      return new ServiceList();
   }

   public ServiceType createServiceType() {
      return new ServiceType();
   }

   public Endpoint createEndpoint() {
      return new Endpoint();
   }
}
