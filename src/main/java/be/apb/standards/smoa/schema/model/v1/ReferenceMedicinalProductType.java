package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceMedicinalProductType",
   propOrder = {"medicinalProductId"}
)
public class ReferenceMedicinalProductType extends AbstractMedicinalProductType {
   @XmlElement(
      required = true
   )
   protected AbstractMedicinalProductIdType medicinalProductId;

   public AbstractMedicinalProductIdType getMedicinalProductId() {
      return this.medicinalProductId;
   }

   public void setMedicinalProductId(AbstractMedicinalProductIdType value) {
      this.medicinalProductId = value;
   }
}
