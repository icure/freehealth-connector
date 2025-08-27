package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractPersonType"
)
@XmlSeeAlso({MaxSetPersonType.class, MaxSetContactPersonType.class, PersonType.class, ReferencePersonType.class})
public abstract class AbstractPersonType extends AbstractEntityType {
}
