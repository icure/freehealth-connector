//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.11.10 à 11:53:46 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20150901.org.w3.xmlenc;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CipherDataType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="CipherDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="CipherValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}CipherReference"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CipherDataType", propOrder = {
    "cipherReference",
    "cipherValue"
})
@XmlRootElement(name = "CipherData")
public class CipherData
    implements Serializable
{

    private final static long serialVersionUID = 20150901L;
    @XmlElement(name = "CipherReference")
    protected CipherReference cipherReference;
    @XmlElement(name = "CipherValue")
    protected byte[] cipherValue;

    /**
     * Obtient la valeur de la propriété cipherReference.
     *
     * @return
     *     possible object is
     *     {@link CipherReference }
     *
     */
    public CipherReference getCipherReference() {
        return cipherReference;
    }

    /**
     * Définit la valeur de la propriété cipherReference.
     *
     * @param value
     *     allowed object is
     *     {@link CipherReference }
     *
     */
    public void setCipherReference(CipherReference value) {
        this.cipherReference = value;
    }

    /**
     * Obtient la valeur de la propriété cipherValue.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCipherValue() {
        return cipherValue;
    }

    /**
     * Définit la valeur de la propriété cipherValue.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCipherValue(byte[] value) {
        this.cipherValue = value;
    }

}
