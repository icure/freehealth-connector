package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCARENETTHIRDPAYERCONTRACT;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"cd"}
)
public class Thirdpayercontract implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDCARENETTHIRDPAYERCONTRACT cd;

   public CDCARENETTHIRDPAYERCONTRACT getCd() {
      return this.cd;
   }

   public void setCd(CDCARENETTHIRDPAYERCONTRACT value) {
      this.cd = value;
   }
}
