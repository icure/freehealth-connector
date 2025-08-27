package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.ContractType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenerateContractResponseType",
   propOrder = {"contract"}
)
@XmlRootElement(
   name = "GenerateContractResponse"
)
public class GenerateContractResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Contract"
   )
   protected ContractType contract;

   public ContractType getContract() {
      return this.contract;
   }

   public void setContract(ContractType value) {
      this.contract = value;
   }
}
