//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2025.10.29 à 10:42:52 AM CET
//


package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.AttestationType;
import be.fgov.ehealth.mediprimaUma.core.v1.StatusResponseType;

import javax.annotation.Generated;
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
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:42:52+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SendUrgentMedicalAidAttestationResponseType
    extends StatusResponseType
{

    @XmlElement(name = "Attestion")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:42:52+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AttestationType attestion;

    /**
     * Obtient la valeur de la propriété attestion.
     *
     * @return
     *     possible object is
     *     {@link AttestationType }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:42:52+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:42:52+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAttestion(AttestationType value) {
        this.attestion = value;
    }

}
