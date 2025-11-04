
package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.AttestationType;
import be.fgov.ehealth.mediprimaUma.core.v1.StatusResponseType;

import javax.xml.bind.annotation.*;


/**
 * Send urgent medical aid attestation response
 *
 * <p>Classe Java pour SendUrgentMedicalAidAttestationResponseType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="SendUrgentMedicalAidAttestationResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}StatusResponseType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Attestion" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}AttestationType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(
        name = "SendUrgentMedicalAidAttestationResponse"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendUrgentMedicalAidAttestationResponseType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "attestion"
})
public class SendUrgentMedicalAidAttestationResponseType
    extends StatusResponseType
{

    @XmlElement(name = "Attestion")
    protected AttestationType attestion;

    /**
     * Obtient la valeur de la propriété attestion.
     *
     * @return
     *     possible object is
     *     {@link AttestationType }
     *
     */
    public AttestationType getAttestion() {
        return attestion;
    }

    /**
     * Définit la valeur de la propriété attestion.
     *
     * @param value
     *     allowed object is
     *     {@link AttestationType }
     *
     */
    public void setAttestion(AttestationType value) {
        this.attestion = value;
    }

}
