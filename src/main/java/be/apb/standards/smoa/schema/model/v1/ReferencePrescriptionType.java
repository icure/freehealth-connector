package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferencePrescriptionType",
   propOrder = {"prescriptionId"}
)
public class ReferencePrescriptionType extends AbstractPrescriptionType {
   @XmlElement(
      required = true
   )
   protected AbstractPrescriptionIdType prescriptionId;

   public AbstractPrescriptionIdType getPrescriptionId() {
      return this.prescriptionId;
   }

   public void setPrescriptionId(AbstractPrescriptionIdType value) {
      this.prescriptionId = value;
   }
}
