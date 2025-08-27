package be.ehealth.apb.gfddpp.services.tipsystem;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;

@WebServiceClient(
   name = "TIPService",
   targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
   wsdlLocation = "https://services-int.ehealth.fgov.be/GFDDPP/TIP/v1?WSDL"
)
public class TIPService extends Service {
   private static final URL TIPSERVICE_WSDL_LOCATION;
   private static final Logger logger = Logger.getLogger(TIPService.class.getName());

   public TIPService(URL var1, QName var2) {
      super(var1, var2);
   }

   public TIPService() {
      super(TIPSERVICE_WSDL_LOCATION, new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "TIPService"));
   }

   @WebEndpoint(
      name = "TIPServiceSOAP11"
   )
   public TIPPortType getTIPServiceSOAP11() {
      return (TIPPortType)super.getPort(new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "TIPServiceSOAP11"), TIPPortType.class);
   }

   @WebEndpoint(
      name = "TIPServiceSOAP11"
   )
   public TIPPortType getTIPServiceSOAP11(WebServiceFeature... var1) {
      return (TIPPortType)super.getPort(new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "TIPServiceSOAP11"), TIPPortType.class, var1);
   }

   static {
      URL var0 = null;

      try {
         URL var1 = TIPService.class.getResource(".");
         var0 = new URL(var1, "https://services-int.ehealth.fgov.be/GFDDPP/TIP/v1?WSDL");
      } catch (MalformedURLException var2) {
         logger.warning("Failed to create URL for the wsdl Location: 'https://services-int.ehealth.fgov.be/GFDDPP/TIP/v1?WSDL', retrying as a local file");
         logger.warning(var2.getMessage());
      }

      TIPSERVICE_WSDL_LOCATION = var0;
   }
}
