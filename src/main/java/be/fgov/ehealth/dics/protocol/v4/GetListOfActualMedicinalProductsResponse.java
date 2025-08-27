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
   name = "GetListOfActualMedicinalProductsResponseType",
   propOrder = {"amps"}
)
@XmlRootElement(
   name = "GetListOfActualMedicinalProductsResponse"
)
public class GetListOfActualMedicinalProductsResponse extends ListConsultationResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Amp"
   )
   protected List<AmpListType> amps;

   public List<AmpListType> getAmps() {
      if (this.amps == null) {
         this.amps = new ArrayList();
      }

      return this.amps;
   }
}
