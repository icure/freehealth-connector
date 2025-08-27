package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.InscriptionType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InsertInscriptionRequestType",
   propOrder = {"inscription"}
)
@XmlRootElement(
   name = "InsertInscriptionRequest"
)
public class InsertInscriptionRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Inscription",
      required = true
   )
   protected InscriptionType inscription;

   public InscriptionType getInscription() {
      return this.inscription;
   }

   public void setInscription(InscriptionType value) {
      this.inscription = value;
   }
}
