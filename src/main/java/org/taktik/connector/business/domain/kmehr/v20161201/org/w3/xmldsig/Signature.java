//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.org.w3.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour SignatureType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="SignatureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}SignedInfo"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}SignatureValue"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}KeyInfo" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Object" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureType", propOrder = {
    "signedInfo",
    "signatureValue",
    "keyInfo",
    "objects"
})
@XmlRootElement(name = "Signature")
public class Signature
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(name = "SignedInfo", required = true)
    protected SignedInfo signedInfo;
    @XmlElement(name = "SignatureValue", required = true)
    protected SignatureValue signatureValue;
    @XmlElement(name = "KeyInfo")
    protected KeyInfo keyInfo;
    @XmlElement(name = "Object")
    protected List<Object> objects;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtient la valeur de la propriété signedInfo.
     *
     * @return
     *     possible object is
     *     {@link SignedInfo }
     *
     */
    public SignedInfo getSignedInfo() {
        return signedInfo;
    }

    /**
     * Définit la valeur de la propriété signedInfo.
     *
     * @param value
     *     allowed object is
     *     {@link SignedInfo }
     *
     */
    public void setSignedInfo(SignedInfo value) {
        this.signedInfo = value;
    }

    /**
     * Obtient la valeur de la propriété signatureValue.
     *
     * @return
     *     possible object is
     *     {@link SignatureValue }
     *
     */
    public SignatureValue getSignatureValue() {
        return signatureValue;
    }

    /**
     * Définit la valeur de la propriété signatureValue.
     *
     * @param value
     *     allowed object is
     *     {@link SignatureValue }
     *
     */
    public void setSignatureValue(SignatureValue value) {
        this.signatureValue = value;
    }

    /**
     * Obtient la valeur de la propriété keyInfo.
     *
     * @return
     *     possible object is
     *     {@link KeyInfo }
     *
     */
    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    /**
     * Définit la valeur de la propriété keyInfo.
     *
     * @param value
     *     allowed object is
     *     {@link KeyInfo }
     *
     */
    public void setKeyInfo(KeyInfo value) {
        this.keyInfo = value;
    }

    /**
     * Gets the value of the objects property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objects property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjects().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     *
     *
     */
    public List<Object> getObjects() {
        if (objects == null) {
            objects = new ArrayList<Object>();
        }
        return this.objects;
    }

    /**
     * Obtient la valeur de la propriété id.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

}
