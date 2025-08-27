//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:06 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * <p>Classe Java pour lnkType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="lnkType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *       &lt;attribute name="TYPE" use="required" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-LNKvalues" />
 *       &lt;attribute name="MEDIATYPE" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-MEDIATYPEvalues" />
 *       &lt;attribute name="URL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SIZE" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lnkType", propOrder = {
    "value"
})
public class LnkType implements Serializable
{

    private final static long serialVersionUID = 20121001L;
    @XmlValue
    protected byte[] value;
    @XmlAttribute(name = "TYPE", required = true)
    protected CDLNKvalues type;
    @XmlAttribute(name = "MEDIATYPE")
    protected CDMEDIATYPEvalues mediatype;
    @XmlAttribute(name = "URL")
    protected String url;
    @XmlAttribute(name = "SIZE")
    protected String size;

    /**
     * Obtient la valeur de la propriété value.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setValue(byte[] value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     *
     * @return
     *     possible object is
     *     {@link CDLNKvalues }
     *
     */
    public CDLNKvalues getTYPE() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     *
     * @param value
     *     allowed object is
     *     {@link CDLNKvalues }
     *
     */
    public void setTYPE(CDLNKvalues value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété mediatype.
     *
     * @return
     *     possible object is
     *     {@link CDMEDIATYPEvalues }
     *
     */
    public CDMEDIATYPEvalues getMEDIATYPE() {
        return mediatype;
    }

    /**
     * Définit la valeur de la propriété mediatype.
     *
     * @param value
     *     allowed object is
     *     {@link CDMEDIATYPEvalues }
     *
     */
    public void setMEDIATYPE(CDMEDIATYPEvalues value) {
        this.mediatype = value;
    }

    /**
     * Obtient la valeur de la propriété url.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getURL() {
        return url;
    }

    /**
     * Définit la valeur de la propriété url.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Obtient la valeur de la propriété size.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSIZE() {
        return size;
    }

    /**
     * Définit la valeur de la propriété size.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSIZE(String value) {
        this.size = value;
    }

}
