//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.10.29 à 10:32:32 AM CET 
//


package be.fgov.ehealth.mediprimaUma.core.v1;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * URI code of the status
 * 
 * <p>Classe Java pour StatusCodeType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StatusCodeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:be:fgov:ehealth:commons:core:v2}StatusCode" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Value" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusCodeType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
    "statusCode"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class StatusCodeType {

    @XmlElement(name = "StatusCode", namespace = "urn:be:fgov:ehealth:commons:core:v2")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected StatusCodeType statusCode;
    @XmlAttribute(name = "Value", required = true)
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String value;

    /**
     * Obtient la valeur de la propriété statusCode.
     * 
     * @return
     *     possible object is
     *     {@link StatusCodeType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStatusCode(StatusCodeType value) {
        this.statusCode = value;
    }

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setValue(String value) {
        this.value = value;
    }

}
