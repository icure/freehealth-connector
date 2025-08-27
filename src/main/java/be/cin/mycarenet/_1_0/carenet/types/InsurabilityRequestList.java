package be.cin.mycarenet._1_0.carenet.types;

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
   name = "InsurabilityRequestListType",
   propOrder = {"singleInsurabilityRequests"}
)
@XmlRootElement(
   name = "InsurabilityRequestList"
)
public class InsurabilityRequestList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SingleInsurabilityRequest",
      required = true
   )
   protected List<SingleInsurabilityRequest> singleInsurabilityRequests;

   public List<SingleInsurabilityRequest> getSingleInsurabilityRequests() {
      if (this.singleInsurabilityRequests == null) {
         this.singleInsurabilityRequests = new ArrayList();
      }

      return this.singleInsurabilityRequests;
   }
}
