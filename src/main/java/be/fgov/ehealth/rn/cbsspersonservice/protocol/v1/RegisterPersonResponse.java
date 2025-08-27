package be.fgov.ehealth.rn.cbsspersonservice.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonDeclarationType;
import be.fgov.ehealth.rn.cbsspersonservice.core.v1.RegisterPersonResultType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegisterPersonResponseType",
   propOrder = {"declaration", "result"}
)
@XmlRootElement(
   name = "RegisterPersonResponse"
)
public class RegisterPersonResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Declaration"
   )
   protected RegisterPersonDeclarationType declaration;
   @XmlElement(
      name = "Result"
   )
   protected RegisterPersonResultType result;

   public RegisterPersonDeclarationType getDeclaration() {
      return this.declaration;
   }

   public void setDeclaration(RegisterPersonDeclarationType value) {
      this.declaration = value;
   }

   public RegisterPersonResultType getResult() {
      return this.result;
   }

   public void setResult(RegisterPersonResultType value) {
      this.result = value;
   }
}
