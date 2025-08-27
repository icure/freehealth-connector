package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetExistingApplicationIdsRequestType",
   propOrder = {"organizationIdentifier"}
)
@XmlRootElement(
   name = "GetExistingApplicationIdsRequest"
)
public class GetExistingApplicationIdsRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OrganizationIdentifier",
      required = true
   )
   protected Id organizationIdentifier;

   public Id getOrganizationIdentifier() {
      return this.organizationIdentifier;
   }

   public void setOrganizationIdentifier(Id value) {
      this.organizationIdentifier = value;
   }
}
