package be.cin.nippin.memberdata.saml.extension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.protocol.Response;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"responses"}
)
@XmlRootElement(
   name = "ResponseList"
)
public class ResponseList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Response",
      namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
      required = true
   )
   protected List<Response> responses;

   public List<Response> getResponses() {
      if (this.responses == null) {
         this.responses = new ArrayList();
      }

      return this.responses;
   }
}
