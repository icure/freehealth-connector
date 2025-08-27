package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificationType", propOrder = {"text", "kmehrmessage"})
@XmlRootElement(name = "notification")
public class RecipeNotification {
	@XmlSchemaType(name = "text")
	String text;
	@XmlSchemaType(name = "kmehrmessage")
	Kmehrmessage kmehrmessage;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Kmehrmessage getKmehrmessage() {
		return kmehrmessage;
	}

	public void setKmehrmessage(Kmehrmessage kmehrmessage) {
		this.kmehrmessage = kmehrmessage;
	}
}
