
package be.fgov.ehealth.mediprimaUma.protocol;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteUrgentMedicalAidAttestationResponseType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "attestionNumber"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class DeleteUrgentMedicalAidAttestationResponseType
    extends StatusResponseType
{

    @XmlElement(name = "AttestionNumber")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String attestionNumber;

    /**
     * Obtient la valeur de la propriété attestionNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAttestionNumber(String value) {
        this.attestionNumber = value;
    }

}
