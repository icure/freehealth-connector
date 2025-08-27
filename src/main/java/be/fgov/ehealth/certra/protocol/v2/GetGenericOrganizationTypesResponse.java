package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.OrganizationIdentifierType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetGenericOrganizationTypesResponseType",
   propOrder = {"organizationTypes"}
)
@XmlRootElement(
   name = "GetGenericOrganizationTypesResponse"
)
public class GetGenericOrganizationTypesResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OrganizationType"
   )
   protected List<OrganizationIdentifierType> organizationTypes;

   public List<OrganizationIdentifierType> getOrganizationTypes() {
      if (this.organizationTypes == null) {
         this.organizationTypes = new ArrayList();
      }

      return this.organizationTypes;
   }
}
