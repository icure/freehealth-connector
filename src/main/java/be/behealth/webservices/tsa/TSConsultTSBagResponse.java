package be.behealth.webservices.tsa;

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
   name = "TSConsultTSBagResponseType",
   propOrder = {"error", "tsBags"}
)
@XmlRootElement(
   name = "TSConsultTSBagResponse"
)
public class TSConsultTSBagResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Error"
   )
   protected ErrorType error;
   @XmlElement(
      name = "TSBag"
   )
   protected List<TSBagType> tsBags;

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }

   public List<TSBagType> getTSBags() {
      if (this.tsBags == null) {
         this.tsBags = new ArrayList();
      }

      return this.tsBags;
   }
}
