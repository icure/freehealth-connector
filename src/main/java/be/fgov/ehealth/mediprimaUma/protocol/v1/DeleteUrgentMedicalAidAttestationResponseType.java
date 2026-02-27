
package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.StatusResponseType;

import javax.xml.bind.annotation.*;


/**
 * Delete urgent medical aid attestation result
 *
 * <p>Classe Java pour DeleteUrgentMedicalAidAttestationResponseType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="DeleteUrgentMedicalAidAttestationResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}StatusResponseType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="AttestionNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(
        name = "DeleteUrgentMedicalAidAttestationResponse"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteUrgentMedicalAidAttestationResponseType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "attestionNumber"
})
public class DeleteUrgentMedicalAidAttestationResponseType
    extends StatusResponseType
{

    @XmlElement(name = "AttestionNumber")
    protected String attestionNumber;

    /**
     * Obtient la valeur de la propriété attestionNumber.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAttestionNumber() {
        return attestionNumber;
    }

    /**
     * Définit la valeur de la propriété attestionNumber.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAttestionNumber(String value) {
        this.attestionNumber = value;
    }

}
