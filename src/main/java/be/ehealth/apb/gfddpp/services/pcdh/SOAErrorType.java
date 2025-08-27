package be.ehealth.apb.gfddpp.services.pcdh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SOAErrorType",
   namespace = "urn:be:fgov:ehealth:errors:soa:v1",
   propOrder = {"environment"}
)
@XmlSeeAlso({SystemError.class, BusinessError.class})
public class SOAErrorType extends ErrorType {
   @XmlElement(
      name = "Environment",
      namespace = "urn:be:fgov:ehealth:errors:soa:v1",
      required = true
   )
   protected String environment;

   public String getEnvironment() {
      return this.environment;
   }

   public void setEnvironment(String var1) {
      this.environment = var1;
   }
}
