
package be.fgov.ehealth.mediprima.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.mediprima.core.v2.ConsultCarmedDataType;

import javax.xml.bind.annotation.*;


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
@XmlRootElement(
        name = "ConsultCarmedInterventionRequest",
        namespace = "urn:be:fgov:ehealth:mediprima:protocol:v2"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultCarmedInterventionRequestType", propOrder = {
    "selectionCriteria"
})
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
