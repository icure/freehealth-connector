
package be.fgov.ehealth.mediprimaUma.protocol;

import be.fgov.ehealth.mediprimaUma.core.StatusType;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * eHealth SOA Response Type for Services that require a Status as output.
 *
 * <p>Classe Java pour StatusResponseType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="StatusResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}ResponseType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:be:fgov:ehealth:commons:core:v2}Status"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusResponseType", propOrder = {
    "status"
})
@XmlSeeAlso({
    SearchUrgentMedicalAidAttestationResponseType.class,
    DeleteUrgentMedicalAidAttestationResponseType.class,
    SendUrgentMedicalAidAttestationResponseType.class,
    PaginationStatusResponseType.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class StatusResponseType
    extends ResponseType
{

    @XmlElement(name = "Status", namespace = "urn:be:fgov:ehealth:commons:core:v2", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected StatusType status;

    /**
     * Obtient la valeur de la propriété status.
     *
     * @return
     *     possible object is
     *     {@link StatusType }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public StatusType getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     *
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStatus(StatusType value) {
        this.status = value;
    }

}
