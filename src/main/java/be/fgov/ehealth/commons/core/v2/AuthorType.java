package be.fgov.ehealth.commons.core.v2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
    "hcParty"
})
public class AuthorType {

    @XmlElement(name = "HcParty", namespace = "urn:be:fgov:ehealth:commons:core:v2", required = true)
    protected List<ActorType> hcParty;

    public List<ActorType> getHcParty() {
        if (hcParty == null) {
            hcParty = new ArrayList<>();
        }
        return this.hcParty;
    }
}
