
package be.fgov.ehealth.mediprima.core.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CarmedAttributedListType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="CarmedAttributedListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Carmed" type="{urn:be:fgov:ehealth:mediprima:core:v2}ConsultCarmedInterventionResultType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarmedAttributedListType", propOrder = {
    "carmed"
})
public class CarmedAttributedListType {

    @XmlElement(name = "Carmed", required = true)
    protected List<ConsultCarmedInterventionResultType> carmed;

    /**
     * Gets the value of the carmed property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carmed property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarmed().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsultCarmedInterventionResultType }
     *
     *
     */
    public List<ConsultCarmedInterventionResultType> getCarmed() {
        if (carmed == null) {
            carmed = new ArrayList<ConsultCarmedInterventionResultType>();
        }
        return this.carmed;
    }

}
