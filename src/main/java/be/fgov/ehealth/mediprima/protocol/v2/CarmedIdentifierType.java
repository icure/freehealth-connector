
package be.fgov.ehealth.mediprima.protocol.v2;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CarmedIdentifierType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CarmedIdentifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CarmedNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VersionNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Beneficiary" type="{urn:be:fgov:ehealth:mediprima:core:v2}BeneficiaryType"/>
 *         &lt;element name="ValidityPeriod" type="{urn:be:fgov:ehealth:mediprima:core:v2}PeriodType"/>
 *         &lt;element name="Pswc" type="{urn:be:fgov:ehealth:mediprima:core:v2}OcmwCpasType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarmedIdentifierType", namespace = "urn:be:fgov:ehealth:mediprima:core:v2", propOrder = {
    "carmedNumber",
    "versionNumber",
    "beneficiary",
    "validityPeriod",
    "pswc"
})
public class CarmedIdentifierType {

    @XmlElement(name = "CarmedNumber", required = true)
    protected String carmedNumber;
    @XmlElement(name = "VersionNumber", required = true)
    protected BigInteger versionNumber;
    @XmlElement(name = "Beneficiary", required = true)
    protected BeneficiaryType beneficiary;
    @XmlElement(name = "ValidityPeriod", required = true)
    protected PeriodType validityPeriod;
    @XmlElement(name = "Pswc", required = true)
    protected OcmwCpasType pswc;

    /**
     * Obtient la valeur de la propriété carmedNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarmedNumber() {
        return carmedNumber;
    }

    /**
     * Définit la valeur de la propriété carmedNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarmedNumber(String value) {
        this.carmedNumber = value;
    }

    /**
     * Obtient la valeur de la propriété versionNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVersionNumber() {
        return versionNumber;
    }

    /**
     * Définit la valeur de la propriété versionNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVersionNumber(BigInteger value) {
        this.versionNumber = value;
    }

    /**
     * Obtient la valeur de la propriété beneficiary.
     * 
     * @return
     *     possible object is
     *     {@link BeneficiaryType }
     *     
     */
    public BeneficiaryType getBeneficiary() {
        return beneficiary;
    }

    /**
     * Définit la valeur de la propriété beneficiary.
     * 
     * @param value
     *     allowed object is
     *     {@link BeneficiaryType }
     *     
     */
    public void setBeneficiary(BeneficiaryType value) {
        this.beneficiary = value;
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
     * Obtient la valeur de la propriété pswc.
     * 
     * @return
     *     possible object is
     *     {@link OcmwCpasType }
     *     
     */
    public OcmwCpasType getPswc() {
        return pswc;
    }

    /**
     * Définit la valeur de la propriété pswc.
     * 
     * @param value
     *     allowed object is
     *     {@link OcmwCpasType }
     *     
     */
    public void setPswc(OcmwCpasType value) {
        this.pswc = value;
    }

}
