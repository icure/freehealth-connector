package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Abstract-PrescriptionIdType"
)
@XmlSeeAlso({LocalPrescriptionIdType.class, RecipeIdType.class})
public abstract class AbstractPrescriptionIdType {
}
