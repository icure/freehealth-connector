package be.fgov.ehealth.errors.soa.v1;

import be.fgov.ehealth.errors.core.v1.ErrorType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SOAErrorType",
   propOrder = {"environment"}
)
@XmlSeeAlso({BusinessError.class, SystemError.class})
public class SOAErrorType extends ErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Environment",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected EnvironmentType environment;

   public EnvironmentType getEnvironment() {
      return this.environment;
   }

   public void setEnvironment(EnvironmentType value) {
      this.environment = value;
   }
}
