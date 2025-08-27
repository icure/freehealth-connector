package be.fgov.ehealth.rn.registries.commons.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AnomaliesType",
   propOrder = {"anomalies"}
)
public class AnomaliesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Anomaly",
      required = true
   )
   protected List anomalies;

   public List getAnomalies() {
      if (this.anomalies == null) {
         this.anomalies = new ArrayList();
      }

      return this.anomalies;
   }
}
