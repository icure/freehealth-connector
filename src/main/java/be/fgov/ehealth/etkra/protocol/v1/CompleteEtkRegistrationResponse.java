package be.fgov.ehealth.etkra.protocol.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"etk"}
)
@XmlRootElement(
   name = "CompleteEtkRegistrationResponse"
)
public class CompleteEtkRegistrationResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ETK"
   )
   protected byte[] etk;

   public byte[] getETK() {
      return this.etk;
   }

   public void setETK(byte[] value) {
      this.etk = value;
   }
}
