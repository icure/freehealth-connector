
package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.CriteriaType;
import be.fgov.ehealth.mediprimaUma.core.v1.RequestType;

import javax.xml.bind.annotation.*;


/**
 * Search urgent medical aid attestation with critera
 *
 * <p>Classe Java pour SearchUrgentMedicalAidAttestationRequestType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="SearchUrgentMedicalAidAttestationRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}RequestType">
 *       &lt;sequence>
 *         &lt;element name="Criteria" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}CriteriaType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(
        name = "SearchUrgentMedicalAidAttestationRequest"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchUrgentMedicalAidAttestationRequestType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "criteria"
})
public class SearchUrgentMedicalAidAttestationRequestType
    extends RequestType
{

    @XmlElement(name = "Criteria", required = true)
    protected CriteriaType criteria;

    /**
     * Obtient la valeur de la propriété criteria.
     *
     * @return
     *     possible object is
     *     {@link CriteriaType }
     *
     */
    public CriteriaType getCriteria() {
        return criteria;
    }

    /**
     * Définit la valeur de la propriété criteria.
     *
     * @param value
     *     allowed object is
     *     {@link CriteriaType }
     *
     */
    public void setCriteria(CriteriaType value) {
        this.criteria = value;
    }

}
