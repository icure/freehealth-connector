
package be.fgov.ehealth.mediprima.protocol.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Identifies the authorType of the message
 * 
 * <p>Classe Java pour AuthorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AuthorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:be:fgov:ehealth:commons:core:v2}HcParty" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
    "hcParty"
})
public class AuthorType {

    @XmlElement(name = "HcParty", namespace = "urn:be:fgov:ehealth:commons:core:v2", required = true)
    protected List<ActorType> hcParty;

    /**
     * Gets the value of the hcParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hcParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHcParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActorType }
     * 
     * 
     */
    public List<ActorType> getHcParty() {
        if (hcParty == null) {
            hcParty = new ArrayList<ActorType>();
        }
        return this.hcParty;
    }

}
