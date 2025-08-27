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
   name = "InsurabilityResponseListType",
   propOrder = {"singleInsurabilityResponses"}
)
@XmlRootElement(
   name = "InsurabilityResponseList"
)
public class InsurabilityResponseList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SingleInsurabilityResponse",
      required = true
   )
   protected List<SingleInsurabilityResponse> singleInsurabilityResponses;

   public List<SingleInsurabilityResponse> getSingleInsurabilityResponses() {
      if (this.singleInsurabilityResponses == null) {
         this.singleInsurabilityResponses = new ArrayList();
      }

      return this.singleInsurabilityResponses;
   }
}
