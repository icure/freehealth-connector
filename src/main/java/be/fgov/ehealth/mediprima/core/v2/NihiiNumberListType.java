
package be.fgov.ehealth.mediprima.core.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour NihiiNumberListType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NihiiNumberListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NihiiNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NihiiNumberListType", propOrder = {
    "nihiiNumber"
})
@XmlSeeAlso({
    be.fgov.ehealth.mediprima.core.v2.ParamedicType.ProviderList.class
})
public class NihiiNumberListType {

    @XmlElement(name = "NihiiNumber", required = true)
    protected List<String> nihiiNumber;

    /**
     * Gets the value of the nihiiNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nihiiNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNihiiNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNihiiNumber() {
        if (nihiiNumber == null) {
            nihiiNumber = new ArrayList<String>();
        }
        return this.nihiiNumber;
    }

}
