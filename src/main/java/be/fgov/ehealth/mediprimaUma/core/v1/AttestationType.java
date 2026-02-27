
package be.fgov.ehealth.mediprimaUma.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Attestation information
 * 
 * <p>Classe Java pour AttestationType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AttestationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Author" type="{urn:be:fgov:ehealth:commons:core:v2}AuthorType"/>
 *         &lt;element name="BeneficiarySsin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValidityPeriod" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}PeriodType"/>
 *         &lt;element name="MedicalCover" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}MedicalCoverType"/>
 *         &lt;element name="AttestationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttestationType", namespace = "urn:be:fgov:ehealth:mediprima:uma:core:v1", propOrder = {
    "author",
    "beneficiarySsin",
    "validityPeriod",
    "medicalCover",
    "attestationNumber"
})
public class AttestationType {

    @XmlElement(name = "Author", required = true)
    protected AuthorType author;
    @XmlElement(name = "BeneficiarySsin", required = true)
    protected String beneficiarySsin;
    @XmlElement(name = "ValidityPeriod", required = true)
    protected PeriodType validityPeriod;
    @XmlElement(name = "MedicalCover", required = true)
    protected String medicalCover;
    @XmlElement(name = "AttestationNumber", required = true)
    protected String attestationNumber;

    /**
     * Obtient la valeur de la propriété author.
     * 
     * @return
     *     possible object is
     *     {@link AuthorType }
     *     
     */
    public AuthorType getAuthor() {
        return author;
    }

    /**
     * Définit la valeur de la propriété author.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorType }
     *     
     */
    public void setAuthor(AuthorType value) {
        this.author = value;
    }

    /**
     * Obtient la valeur de la propriété beneficiarySsin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarySsin() {
        return beneficiarySsin;
    }

    /**
     * Définit la valeur de la propriété beneficiarySsin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarySsin(String value) {
        this.beneficiarySsin = value;
    }

    /**
     * Obtient la valeur de la propriété validityPeriod.
     * 
     * @return
     *     possible object is
     *     {@link PeriodType }
     *     
     */
    public PeriodType getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * Définit la valeur de la propriété validityPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodType }
     *     
     */
    public void setValidityPeriod(PeriodType value) {
        this.validityPeriod = value;
    }

    /**
     * Obtient la valeur de la propriété medicalCover.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicalCover() {
        return medicalCover;
    }

    /**
     * Définit la valeur de la propriété medicalCover.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicalCover(String value) {
        this.medicalCover = value;
    }

    /**
     * Obtient la valeur de la propriété attestationNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttestationNumber() {
        return attestationNumber;
    }

    /**
     * Définit la valeur de la propriété attestationNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttestationNumber(String value) {
        this.attestationNumber = value;
    }

}
