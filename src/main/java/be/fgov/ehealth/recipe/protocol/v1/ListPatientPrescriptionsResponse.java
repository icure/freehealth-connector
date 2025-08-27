package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPatientPrescriptionsResponseType",
   propOrder = {"securedListPatientPrescriptionsResponse"}
)
@XmlRootElement(
   name = "ListPatientPrescriptionsResponse"
)
public class ListPatientPrescriptionsResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredListPatientPrescriptionsResponse"
   )
   protected SecuredContentType securedListPatientPrescriptionsResponse;

   public SecuredContentType getSecuredListPatientPrescriptionsResponse() {
      return this.securedListPatientPrescriptionsResponse;
   }

   public void setSecuredListPatientPrescriptionsResponse(SecuredContentType value) {
      this.securedListPatientPrescriptionsResponse = value;
   }
}
