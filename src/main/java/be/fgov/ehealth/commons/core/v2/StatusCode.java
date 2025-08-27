package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

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
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String value;

   public StatusCode getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(StatusCode value) {
      this.statusCode = value;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
