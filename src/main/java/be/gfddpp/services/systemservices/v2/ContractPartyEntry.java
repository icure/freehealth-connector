package be.gfddpp.services.systemservices.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "contractPartyEntry",
   propOrder = {"contractPartyType", "contractPartyID"}
)
public class ContractPartyEntry {
   @XmlElement(
      required = true
   )
   protected String contractPartyType;
   @XmlElement(
      required = true
   )
   protected String contractPartyID;

   public String getContractPartyType() {
      return this.contractPartyType;
   }

   public void setContractPartyType(String value) {
      this.contractPartyType = value;
   }

   public String getContractPartyID() {
      return this.contractPartyID;
   }

   public void setContractPartyID(String value) {
      this.contractPartyID = value;
   }
}
