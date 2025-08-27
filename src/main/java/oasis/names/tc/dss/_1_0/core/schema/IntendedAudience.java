package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"recipients"}
)
@XmlRootElement(
   name = "IntendedAudience"
)
public class IntendedAudience implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Recipient",
      required = true
   )
   protected List<NameIdentifierType> recipients;

   public List<NameIdentifierType> getRecipients() {
      if (this.recipients == null) {
         this.recipients = new ArrayList();
      }

      return this.recipients;
   }
}
