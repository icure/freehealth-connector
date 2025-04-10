
package be.fgov.ehealth.mediprima.protocol.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ConsultCarmedInterventionResponseType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ConsultCarmedInterventionResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}StatusResponseType">
 *       &lt;sequence>
 *         &lt;element name="SelectionCriteria" type="{urn:be:fgov:ehealth:mediprima:core:v1}ConsultCarmedDataType"/>
 *         &lt;element name="Result" type="{urn:be:fgov:ehealth:mediprima:core:v1}CarmedAttributedListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultCarmedInterventionResponseType", namespace = "urn:be:fgov:ehealth:mediprima:protocol:v1", propOrder = {
    "selectionCriteria",
    "result"
})
public class ConsultCarmedInterventionResponseType
    extends StatusResponseType
{

    @XmlElement(name = "SelectionCriteria", required = true)
    protected ConsultCarmedDataType selectionCriteria;
    @XmlElement(name = "Result")
    protected CarmedAttributedListType result;

    /**
     * Obtient la valeur de la propriété selectionCriteria.
     * 
     * @return
     *     possible object is
     *     {@link ConsultCarmedDataType }
     *     
     */
    public ConsultCarmedDataType getSelectionCriteria() {
        return selectionCriteria;
    }

    /**
     * Définit la valeur de la propriété selectionCriteria.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultCarmedDataType }
     *     
     */
    public void setSelectionCriteria(ConsultCarmedDataType value) {
        this.selectionCriteria = value;
    }

    /**
     * Obtient la valeur de la propriété result.
     * 
     * @return
     *     possible object is
     *     {@link CarmedAttributedListType }
     *     
     */
    public CarmedAttributedListType getResult() {
        return result;
    }

    /**
     * Définit la valeur de la propriété result.
     * 
     * @param value
     *     allowed object is
     *     {@link CarmedAttributedListType }
     *     
     */
    public void setResult(CarmedAttributedListType value) {
        this.result = value;
    }

}
