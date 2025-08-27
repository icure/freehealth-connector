package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusCodeType",
   propOrder = {"statusCode"}
)
@XmlRootElement(
   name = "StatusCode"
)
public class StatusCode implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "StatusCode"
   )
   protected StatusCode statusCode;
   @XmlAttribute(
      name = "Value",
      required = true
   )
   protected QName value;

   public StatusCode getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(StatusCode value) {
      this.statusCode = value;
   }

   public QName getValue() {
      return this.value;
   }

   public void setValue(QName value) {
      this.value = value;
   }
}
