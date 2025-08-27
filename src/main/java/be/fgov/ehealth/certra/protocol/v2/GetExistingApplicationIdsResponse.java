package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
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
   name = "GetExistingApplicationIdsResponseType",
   propOrder = {"applicationIds"}
)
@XmlRootElement(
   name = "GetExistingApplicationIdsResponse"
)
public class GetExistingApplicationIdsResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationId",
      nillable = true
   )
   protected List<String> applicationIds;

   public List<String> getApplicationIds() {
      if (this.applicationIds == null) {
         this.applicationIds = new ArrayList();
      }

      return this.applicationIds;
   }
}
