
package be.fgov.ehealth.mediprima.protocol.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * StatusType of the requests
 * 
 * <p>Classe Java pour StatusType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:be:fgov:ehealth:commons:core:v2}StatusCode"/>
 *         &lt;element ref="{urn:be:fgov:ehealth:commons:core:v2}StatusMessage" minOccurs="0"/>
 *         &lt;element ref="{urn:be:fgov:ehealth:commons:core:v2}StatusDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
    "statusCode",
    "statusMessage",
    "statusDetail"
})
public class StatusType {

    @XmlElement(name = "StatusCode", namespace = "urn:be:fgov:ehealth:commons:core:v2", required = true)
    protected StatusCodeType statusCode;
    @XmlElement(name = "StatusMessage", namespace = "urn:be:fgov:ehealth:commons:core:v2")
    protected String statusMessage;
    @XmlElement(name = "StatusDetail", namespace = "urn:be:fgov:ehealth:commons:core:v2")
    protected StatusDetailType statusDetail;

    /**
     * Obtient la valeur de la propriété statusCode.
     * 
     * @return
     *     possible object is
     *     {@link StatusCodeType }
     *     
     */
    public StatusCodeType getStatusCode() {
        return statusCode;
    }

    /**
     * Définit la valeur de la propriété statusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCodeType }
     *     
     */
    public void setStatusCode(StatusCodeType value) {
        this.statusCode = value;
    }

    /**
     * Obtient la valeur de la propriété statusMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Définit la valeur de la propriété statusMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Obtient la valeur de la propriété statusDetail.
     * 
     * @return
     *     possible object is
     *     {@link StatusDetailType }
     *     
     */
    public StatusDetailType getStatusDetail() {
        return statusDetail;
    }

    /**
     * Définit la valeur de la propriété statusDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusDetailType }
     *     
     */
    public void setStatusDetail(StatusDetailType value) {
        this.statusDetail = value;
    }

}
