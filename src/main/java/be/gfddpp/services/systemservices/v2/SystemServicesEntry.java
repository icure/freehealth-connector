package be.gfddpp.services.systemservices.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "systemServicesEntry",
   propOrder = {"system", "service", "contractList"}
)
public class SystemServicesEntry {
   @XmlElement(
      required = true
   )
   protected System system;
   @XmlElement(
      required = true
   )
   protected List<Service> service;
   protected ContractList contractList;

   public System getSystem() {
      return this.system;
   }

   public void setSystem(System value) {
      this.system = value;
   }

   public List<Service> getService() {
      if (this.service == null) {
         this.service = new ArrayList();
      }

      return this.service;
   }

   public ContractList getContractList() {
      return this.contractList;
   }

   public void setContractList(ContractList value) {
      this.contractList = value;
   }
}
