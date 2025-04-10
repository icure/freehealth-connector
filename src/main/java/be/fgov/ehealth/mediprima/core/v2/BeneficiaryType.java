
package be.fgov.ehealth.mediprima.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour BeneficiaryType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BeneficiaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ssin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BirthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeathDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BeneficiaryType", namespace = "urn:be:fgov:ehealth:mediprima:core:v2", propOrder = {
    "ssin",
    "lastName",
    "firstName",
    "gender",
    "birthDate",
    "deathDate"
})
public class BeneficiaryType {

    @XmlElement(name = "Ssin", required = true)
    protected String ssin;
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "Gender")
    protected String gender;
    @XmlElement(name = "BirthDate")
    protected String birthDate;
    @XmlElement(name = "DeathDate")
    protected String deathDate;

    /**
     * Obtient la valeur de la propriété ssin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsin() {
        return ssin;
    }

    /**
     * Définit la valeur de la propriété ssin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsin(String value) {
        this.ssin = value;
    }

    /**
     * Obtient la valeur de la propriété lastName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Définit la valeur de la propriété lastName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Obtient la valeur de la propriété firstName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Définit la valeur de la propriété firstName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Obtient la valeur de la propriété gender.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Définit la valeur de la propriété gender.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Obtient la valeur de la propriété birthDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Définit la valeur de la propriété birthDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Obtient la valeur de la propriété deathDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeathDate() {
        return deathDate;
    }

    /**
     * Définit la valeur de la propriété deathDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeathDate(String value) {
        this.deathDate = value;
    }

}
