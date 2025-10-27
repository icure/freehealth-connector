
package be.fgov.ehealth.mediprimaUma.protocol;

import be.fgov.ehealth.mediprimaUma.core.CriteriaType;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchUrgentMedicalAidAttestationRequestType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "criteria"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SearchUrgentMedicalAidAttestationRequestType
    extends RequestType
{

    @XmlElement(name = "Criteria", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected CriteriaType criteria;

    /**
     * Obtient la valeur de la propriété criteria.
     *
     * @return
     *     possible object is
     *     {@link CriteriaType }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCriteria(CriteriaType value) {
        this.criteria = value;
    }

}
