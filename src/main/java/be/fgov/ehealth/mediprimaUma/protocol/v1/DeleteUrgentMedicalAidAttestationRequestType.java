
package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.AuthorRequestType;

import javax.xml.bind.annotation.*;


/**
 * Delete urgent medical aid attestation request
 *
 * <p>Classe Java pour DeleteUrgentMedicalAidAttestationRequestType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="DeleteUrgentMedicalAidAttestationRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}AuthorRequestType">
 *       &lt;sequence>
 *         &lt;element name="BeneficiarySsin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AttestationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(
        name = "DeleteUrgentMedicalAidAttestationRequest"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteUrgentMedicalAidAttestationRequestType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "beneficiarySsin",
    "attestationNumber"
})
public class DeleteUrgentMedicalAidAttestationRequestType
    extends AuthorRequestType
{

    @XmlElement(name = "BeneficiarySsin", required = true)
    protected String beneficiarySsin;
    @XmlElement(name = "AttestationNumber", required = true)
    protected String attestationNumber;

    /**
     * Obtient la valeur de la propriété beneficiarySsin.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBeneficiarySsin() {
        return beneficiarySsin;
    }

    /**
     * Définit la valeur de la propriété beneficiarySsin.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBeneficiarySsin(String value) {
        this.beneficiarySsin = value;
    }

    /**
     * Obtient la valeur de la propriété attestationNumber.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAttestationNumber() {
        return attestationNumber;
    }

    /**
     * Définit la valeur de la propriété attestationNumber.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAttestationNumber(String value) {
        this.attestationNumber = value;
    }

}
