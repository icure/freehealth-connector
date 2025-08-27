package be.fgov.ehealth.commons.core.v2;

import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusDetailType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
        "any"
})
public class StatusDetailType {

    @XmlAnyElement(lax = true)
    protected List<Object> any;

    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<>();
        }
        return this.any;
    }
}
