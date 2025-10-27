
package be.fgov.ehealth.mediprimaUma.protocol;

import be.fgov.ehealth.mediprimaUma.core.AttestationType;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendUrgentMedicalAidAttestationResponseType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "attestion"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SendUrgentMedicalAidAttestationResponseType
    extends StatusResponseType
{

    @XmlElement(name = "Attestion")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AttestationType attestion;

    /**
     * Obtient la valeur de la propriété attestion.
     *
     * @return
     *     possible object is
     *     {@link AttestationType }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAttestion(AttestationType value) {
        this.attestion = value;
    }

}
