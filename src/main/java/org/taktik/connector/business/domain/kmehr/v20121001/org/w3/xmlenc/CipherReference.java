//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:06 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20121001.org.w3.xmlenc;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CipherReferenceType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="CipherReferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Transforms" type="{http://www.w3.org/2001/04/xmlenc#}TransformsType" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CipherReferenceType", propOrder = {
    "transforms"
})
@XmlRootElement(name = "CipherReference")
public class CipherReference
    implements Serializable
{

    private final static long serialVersionUID = 20121001L;
    @XmlElement(name = "Transforms")
    protected TransformsType transforms;
    @XmlAttribute(name = "URI", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String uri;

    /**
     * Obtient la valeur de la propriété transforms.
     *
     * @return
     *     possible object is
     *     {@link TransformsType }
     *
     */
    public TransformsType getTransforms() {
        return transforms;
    }

    /**
     * Définit la valeur de la propriété transforms.
     *
     * @param value
     *     allowed object is
     *     {@link TransformsType }
     *
     */
    public void setTransforms(TransformsType value) {
        this.transforms = value;
    }

    /**
     * Obtient la valeur de la propriété uri.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getURI() {
        return uri;
    }

    /**
     * Définit la valeur de la propriété uri.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setURI(String value) {
        this.uri = value;
    }

}
