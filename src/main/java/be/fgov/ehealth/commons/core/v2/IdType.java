package be.fgov.ehealth.commons.core.v2;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdType", namespace = "urn:be:fgov:ehealth:commons:core:v2")
public class IdType {

    @XmlValue
    protected String value;

    @XmlAttribute(name = "Type", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }
}