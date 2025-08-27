package be.fgov.ehealth.rn.cbsspersonservice.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonDeclarationType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegisterPersonRequestType",
   propOrder = {"applicationId", "declaration"}
)
@XmlRootElement(
   name = "RegisterPersonRequest"
)
public class RegisterPersonRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationId",
      required = true
   )
   protected String applicationId;
   @XmlElement(
      name = "Declaration",
      required = true
   )
   protected RegisterPersonDeclarationType declaration;

   public String getApplicationId() {
      return this.applicationId;
   }

   public void setApplicationId(String value) {
      this.applicationId = value;
   }

   public RegisterPersonDeclarationType getDeclaration() {
      return this.declaration;
   }

   public void setDeclaration(RegisterPersonDeclarationType value) {
      this.declaration = value;
   }
}
