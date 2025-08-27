package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForPrescriberResponseType",
   propOrder = {"securedGetPrescriptionForPrescriberResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionForPrescriberResponse"
)
public class GetPrescriptionForPrescriberResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredGetPrescriptionForPrescriberResponse"
   )
   protected SecuredContentType securedGetPrescriptionForPrescriberResponse;

   public SecuredContentType getSecuredGetPrescriptionForPrescriberResponse() {
      return this.securedGetPrescriptionForPrescriberResponse;
   }

   public void setSecuredGetPrescriptionForPrescriberResponse(SecuredContentType value) {
      this.securedGetPrescriptionForPrescriberResponse = value;
   }
}
