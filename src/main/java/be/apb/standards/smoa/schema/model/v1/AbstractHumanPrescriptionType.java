package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractHumanPrescriptionType"
)
@XmlSeeAlso({AbstractPatientPrescriptionType.class, AbstractSpecialPrescriptionType.class, AbstractDeferredPrescriptionType.class})
public abstract class AbstractHumanPrescriptionType extends AbstractPrescriptionType {
}
