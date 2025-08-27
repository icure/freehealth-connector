package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createPrescriptionResult",
   propOrder = {"rid"}
)
@XmlRootElement(
   name = "createPrescriptionResult"
)
public class CreatePrescriptionResult extends ResponseType {
   @XmlElement(
      required = true
   )
   protected String rid;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }
}
