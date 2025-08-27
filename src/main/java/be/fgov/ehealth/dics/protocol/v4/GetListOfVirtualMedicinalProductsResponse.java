package be.fgov.ehealth.dics.protocol.v4;

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
   name = "GetListOfVirtualMedicinalProductsResponseType",
   propOrder = {"vmps"}
)
@XmlRootElement(
   name = "GetListOfVirtualMedicinalProductsResponse"
)
public class GetListOfVirtualMedicinalProductsResponse extends ListConsultationResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Vmp"
   )
   protected List<VmpListType> vmps;

   public List<VmpListType> getVmps() {
      if (this.vmps == null) {
         this.vmps = new ArrayList();
      }

      return this.vmps;
   }
}
