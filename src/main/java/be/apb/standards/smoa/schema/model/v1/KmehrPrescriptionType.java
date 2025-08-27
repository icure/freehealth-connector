package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "KmehrPrescriptionType",
		propOrder = {"id", "any"}
)
public class KmehrPrescriptionType extends AbstractPrescriptionType {
	@XmlElement(
			required = true
	)
	protected AbstractPrescriptionIdType id;
	protected Kmehrmessage kmehrPrescription;
	@XmlAnyElement(
			lax = true
	)
	protected Object any;

	public AbstractPrescriptionIdType getId() {
		return this.id;
	}

	public void setId(AbstractPrescriptionIdType value) {
		this.id = value;
	}

	public Object getAny() {
		return this.any;
	}

	public void setAny(Object value) {
		this.any = value;
	}
}
