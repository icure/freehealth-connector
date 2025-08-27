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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;


/**
 * <p>Classe Java pour addressTypeBase complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="addressTypeBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-ADDRESS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}countryType" minOccurs="0"/>
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="housenumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postboxnumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addressTypeBase", propOrder = {
    "ids",
    "cds",
    "country",
    "zip",
    "nis",
    "city",
    "district",
    "street",
    "housenumber",
    "postboxnumber",
    "texts"
})
@XmlSeeAlso({
    AddressType.class
})
public class AddressTypeBase
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(name = "id")
    protected List<IDKMEHR> ids;
    @XmlElement(name = "cd")
    protected List<CDADDRESS> cds;
    protected CountryType country;
    protected String zip;
    protected String nis;
    protected String city;
    protected String district;
    protected String street;
    protected String housenumber;
    protected String postboxnumber;
    @XmlElement(name = "text")
    protected List<TextType> texts;

    /**
     * Gets the value of the ids property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ids property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIds().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDKMEHR }
     *
     *
     */
    public List<IDKMEHR> getIds() {
        if (ids == null) {
            ids = new ArrayList<IDKMEHR>();
        }
        return this.ids;
    }

    /**
     * Gets the value of the cds property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cds property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCds().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CDADDRESS }
     *
     *
     */
    public List<CDADDRESS> getCds() {
        if (cds == null) {
            cds = new ArrayList<CDADDRESS>();
        }
        return this.cds;
    }

    /**
     * Obtient la valeur de la propriété country.
     *
     * @return
     *     possible object is
     *     {@link CountryType }
     *
     */
    public CountryType getCountry() {
        return country;
    }

    /**
     * Définit la valeur de la propriété country.
     *
     * @param value
     *     allowed object is
     *     {@link CountryType }
     *
     */
    public void setCountry(CountryType value) {
        this.country = value;
    }

    /**
     * Obtient la valeur de la propriété zip.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getZip() {
        return zip;
    }

    /**
     * Définit la valeur de la propriété zip.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Obtient la valeur de la propriété nis.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNis() {
        return nis;
    }

    /**
     * Définit la valeur de la propriété nis.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNis(String value) {
        this.nis = value;
    }

    /**
     * Obtient la valeur de la propriété city.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCity() {
        return city;
    }

    /**
     * Définit la valeur de la propriété city.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Obtient la valeur de la propriété district.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Définit la valeur de la propriété district.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * Obtient la valeur de la propriété street.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStreet() {
        return street;
    }

    /**
     * Définit la valeur de la propriété street.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Obtient la valeur de la propriété housenumber.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHousenumber() {
        return housenumber;
    }

    /**
     * Définit la valeur de la propriété housenumber.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHousenumber(String value) {
        this.housenumber = value;
    }

    /**
     * Obtient la valeur de la propriété postboxnumber.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPostboxnumber() {
        return postboxnumber;
    }

    /**
     * Définit la valeur de la propriété postboxnumber.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPostboxnumber(String value) {
        this.postboxnumber = value;
    }

    /**
     * Gets the value of the texts property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the texts property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTexts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     *
     *
     */
    public List<TextType> getTexts() {
        if (texts == null) {
            texts = new ArrayList<TextType>();
        }
        return this.texts;
    }

}
