package be.fgov.ehealth.messageservices.core.v1;

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
   name = "RetrieveTransactionRequestType",
   propOrder = {"request", "select"}
)
@XmlRootElement(
   name = "RetrieveTransactionRequest",
   namespace = "http://www.ehealth.fgov.be/messageservices/protocol/v1"
)
public class RetrieveTransactionRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected SelectRetrieveTransactionType select;
   @XmlAttribute(
      name = "messageProtocoleSchemaVersion"
   )
   protected BigDecimal messageProtocoleSchemaVersion;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public SelectRetrieveTransactionType getSelect() {
      return this.select;
   }

   public void setSelect(SelectRetrieveTransactionType value) {
      this.select = value;
   }

   public BigDecimal getMessageProtocoleSchemaVersion() {
      return this.messageProtocoleSchemaVersion;
   }

   public void setMessageProtocoleSchemaVersion(BigDecimal value) {
      this.messageProtocoleSchemaVersion = value;
   }
}
