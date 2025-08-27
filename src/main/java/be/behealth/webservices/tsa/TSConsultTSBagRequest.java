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
   name = "TSConsultTSBagRequestType",
   propOrder = {"idHospital", "tsLists"}
)
@XmlRootElement(
   name = "TSConsultTSBagRequest"
)
public class TSConsultTSBagRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IDHospital",
      required = true
   )
   protected String idHospital;
   @XmlElement(
      name = "TSList"
   )
   protected List<TimeStampIdentification> tsLists;

   public String getIDHospital() {
      return this.idHospital;
   }

   public void setIDHospital(String value) {
      this.idHospital = value;
   }

   public List<TimeStampIdentification> getTSLists() {
      if (this.tsLists == null) {
         this.tsLists = new ArrayList();
      }

      return this.tsLists;
   }
}
