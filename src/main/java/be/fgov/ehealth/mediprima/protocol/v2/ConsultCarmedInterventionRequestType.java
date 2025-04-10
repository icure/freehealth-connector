
package be.fgov.ehealth.mediprima.protocol.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ConsultCarmedInterventionRequestType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="ConsultCarmedInterventionRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}RequestType">
 *       &lt;sequence>
 *         &lt;element name="SelectionCriteria" type="{urn:be:fgov:ehealth:mediprima:core:v2}ConsultCarmedDataType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultCarmedInterventionRequestType", namespace = "urn:be:fgov:ehealth:mediprima:protocol:v2", propOrder = {
    "selectionCriteria"
})
@XmlRootElement(
    name = "ConsultCarmedInterventionRequest",
    namespace = "urn:be:fgov:ehealth:mediprima:protocol:v2"
)
public class ConsultCarmedInterventionRequestType
    extends RequestType
{

    @XmlElement(name = "SelectionCriteria", required = true)
    protected ConsultCarmedDataType selectionCriteria;

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

}
