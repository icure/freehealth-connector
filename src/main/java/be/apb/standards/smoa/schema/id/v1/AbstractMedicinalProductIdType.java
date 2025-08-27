package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Abstract-MedicinalProductIdType"
)
@XmlSeeAlso({CnkIdType.class, BeRegistrationIdType.class, AtcDddSystemIdType.class})
public abstract class AbstractMedicinalProductIdType {
}
