package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractMedicationConsumerPatientGroupType"
)
@XmlSeeAlso({MedicationConsumerPatientGroupType.class})
public abstract class AbstractMedicationConsumerPatientGroupType extends AbstractMedicationConsumerType {
}
