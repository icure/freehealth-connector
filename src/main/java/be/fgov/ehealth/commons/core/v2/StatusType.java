package be.fgov.ehealth.commons.core.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
        "statusCode",
        "statusMessage",
        "statusDetail"
})
public class StatusType {

    @XmlElement(name = "StatusCode", namespace = "urn:be:fgov:ehealth:commons:core:v2", required = true)
    protected StatusCodeType statusCode;

    @XmlElement(name = "StatusMessage", namespace = "urn:be:fgov:ehealth:commons:core:v2")
    protected String statusMessage;

    @XmlElement(name = "StatusDetail", namespace = "urn:be:fgov:ehealth:commons:core:v2")
    protected StatusDetailType statusDetail;

    public StatusCodeType getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCodeType value) {
        this.statusCode = value;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    public StatusDetailType getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(StatusDetailType value) {
        this.statusDetail = value;
    }
}
