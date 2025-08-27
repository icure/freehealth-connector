package be.fgov.ehealth.messageservices.mycarenet.core.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1.Kmehrmessage;
import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendTransactionResponseType",
   propOrder = {"response", "acknowledge", "kmehrmessage"}
)
@XmlRootElement(
   name = "SendTransactionResponse",
   namespace = "http://www.ehealth.fgov.be/messageservices/protocol/v1"
)
public class SendTransactionResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected ResponseType response;
   @XmlElement(
      required = true
   )
   protected AcknowledgeType acknowledge;
   protected Kmehrmessage kmehrmessage;
   @XmlAttribute(
      name = "messageProtocoleSchemaVersion"
   )
   protected BigDecimal messageProtocoleSchemaVersion;

   public ResponseType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseType value) {
      this.response = value;
   }

   public AcknowledgeType getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(AcknowledgeType value) {
      this.acknowledge = value;
   }

   public Kmehrmessage getKmehrmessage() {
      return this.kmehrmessage;
   }

   public void setKmehrmessage(Kmehrmessage value) {
      this.kmehrmessage = value;
   }

   public BigDecimal getMessageProtocoleSchemaVersion() {
      return this.messageProtocoleSchemaVersion;
   }

   public void setMessageProtocoleSchemaVersion(BigDecimal value) {
      this.messageProtocoleSchemaVersion = value;
   }
}
