package be.fgov.ehealth.commons.core.v2;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusCodeType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
        "statusCode"
})
public class StatusCodeType {

    @XmlElement(name = "StatusCode", namespace = "urn:be:fgov:ehealth:commons:core:v2")
    protected String statusCode;

    @XmlAttribute(name = "Value", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String value;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
