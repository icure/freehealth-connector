
package be.fgov.ehealth.mediprima.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ConsultCarmedInterventionResultType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ConsultCarmedInterventionResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CarmedIdentifier" type="{urn:be:fgov:ehealth:mediprima:core:v1}CarmedIdentifierType"/>
 *         &lt;element name="CarmedContent" type="{urn:be:fgov:ehealth:mediprima:core:v1}CarmedContentCareType" minOccurs="0"/>
 *         &lt;element name="AgreementNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultCarmedInterventionResultType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
    "carmedIdentifier",
    "carmedContent",
    "agreementNumber"
})
public class ConsultCarmedInterventionResultType {

    @XmlElement(name = "CarmedIdentifier", required = true)
    protected CarmedIdentifierType carmedIdentifier;
    @XmlElement(name = "CarmedContent")
    protected CarmedContentCareType carmedContent;
    @XmlElement(name = "AgreementNumber")
    protected String agreementNumber;

    /**
     * Obtient la valeur de la propriété carmedIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link CarmedIdentifierType }
     *     
     */
    public CarmedIdentifierType getCarmedIdentifier() {
        return carmedIdentifier;
    }

    /**
     * Définit la valeur de la propriété carmedIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link CarmedIdentifierType }
     *     
     */
    public void setCarmedIdentifier(CarmedIdentifierType value) {
        this.carmedIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété carmedContent.
     * 
     * @return
     *     possible object is
     *     {@link CarmedContentCareType }
     *     
     */
    public CarmedContentCareType getCarmedContent() {
        return carmedContent;
    }

    /**
     * Définit la valeur de la propriété carmedContent.
     * 
     * @param value
     *     allowed object is
     *     {@link CarmedContentCareType }
     *     
     */
    public void setCarmedContent(CarmedContentCareType value) {
        this.carmedContent = value;
    }

    /**
     * Obtient la valeur de la propriété agreementNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementNumber() {
        return agreementNumber;
    }

    /**
     * Définit la valeur de la propriété agreementNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementNumber(String value) {
        this.agreementNumber = value;
    }

}
