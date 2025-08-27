package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractSpecialPrescriptionType"
)
@XmlSeeAlso({SpecialPrescriptionType.class})
public abstract class AbstractSpecialPrescriptionType extends AbstractHumanPrescriptionType {
}
