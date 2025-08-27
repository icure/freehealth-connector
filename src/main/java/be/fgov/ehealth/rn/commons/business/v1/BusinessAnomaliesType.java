package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BusinessAnomaliesType",
   propOrder = {"businessAnomalies"}
)
public class BusinessAnomaliesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BusinessAnomaly",
      required = true
   )
   protected List businessAnomalies;

   public List getBusinessAnomalies() {
      if (this.businessAnomalies == null) {
         this.businessAnomalies = new ArrayList();
      }

      return this.businessAnomalies;
   }
}
