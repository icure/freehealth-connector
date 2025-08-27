package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedAdminRequestListType",
   propOrder = {"singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests"}
)
@XmlRootElement(
   name = "MedAdminRequestList"
)
public class MedAdminRequestList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "SingleNurseContractualCareRequest",
   type = SingleNurseContractualCareRequest.class
), @XmlElement(
   name = "SinglePalliativeCareRequest",
   type = SinglePalliativeCareRequest.class
), @XmlElement(
   name = "SingleSpecificTechnicalCareRequest",
   type = SingleSpecificTechnicalCareRequest.class
)})
   protected List<Serializable> singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests;

   public List<Serializable> getSingleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests() {
      if (this.singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests == null) {
         this.singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests = new ArrayList();
      }

      return this.singleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests;
   }
}
