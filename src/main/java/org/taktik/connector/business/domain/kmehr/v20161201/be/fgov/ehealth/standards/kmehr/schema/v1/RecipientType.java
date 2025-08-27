//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * The recipient can be specified by a free combination of hcparty elements (organisation(s), medical specialty(ies), person(s)). If no individual person can be identified as recipient, you should specify a medical specialty or, at
 *         least the identification of the organisation that receives the message for further distribution (a regional server for example).
 *
 *
 * <p>Classe Java pour recipientType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="recipientType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hcparty" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}hcpartyType" maxOccurs="unbounded"/>
 *         &lt;element name="dummyFriend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipientType", propOrder = {
    "hcparties",
    "dummyFriend"
})
@XmlSeeAlso({
    ReciperecipientType.class
})
public class RecipientType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(name = "hcparty", required = true)
    protected List<HcpartyType> hcparties;
    protected String dummyFriend;

    /**
     * Gets the value of the hcparties property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hcparties property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHcparties().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HcpartyType }
     *
     *
     */
    public List<HcpartyType> getHcparties() {
        if (hcparties == null) {
            hcparties = new ArrayList<HcpartyType>();
        }
        return this.hcparties;
    }

    /**
     * Obtient la valeur de la propriété dummyFriend.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDummyFriend() {
        return dummyFriend;
    }

    /**
     * Définit la valeur de la propriété dummyFriend.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDummyFriend(String value) {
        this.dummyFriend = value;
    }

}
