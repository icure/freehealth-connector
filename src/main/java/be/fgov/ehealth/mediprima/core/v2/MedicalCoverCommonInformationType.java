
package be.fgov.ehealth.mediprima.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MedicalCoverCommonInformationType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MedicalCoverCommonInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ValidityPeriod" type="{urn:be:fgov:ehealth:mediprima:core:v2}PeriodType"/>
 *         &lt;element name="PswcSupport" type="{urn:be:fgov:ehealth:mediprima:core:v2}PswcSupportType" minOccurs="0"/>
 *         &lt;element name="AmountPatientPartMax" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AllowedSupplements" type="{urn:be:fgov:ehealth:mediprima:core:v2}AllowedSupplementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedicalCoverCommonInformationType", namespace = "urn:be:fgov:ehealth:mediprima:core:v2", propOrder = {
    "validityPeriod",
    "pswcSupport",
    "amountPatientPartMax",
    "allowedSupplements"
})
@XmlSeeAlso({
    ProsthesisType.class,
    MiscellaneousType.class,
    HospitalizationType.class,
    PharmaceuticalDrugType.class,
    ParamedicType.class,
    MedicalTransportationType.class,
    DoctorType.class
})
public class MedicalCoverCommonInformationType {

    @XmlElement(name = "ValidityPeriod", required = true)
    protected PeriodType validityPeriod;
    @XmlElement(name = "PswcSupport")
    protected PswcSupportType pswcSupport;
    @XmlElement(name = "AmountPatientPartMax")
    protected Integer amountPatientPartMax;
    @XmlElement(name = "AllowedSupplements")
    protected AllowedSupplementsType allowedSupplements;

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
     * Obtient la valeur de la propriété pswcSupport.
     * 
     * @return
     *     possible object is
     *     {@link PswcSupportType }
     *     
     */
    public PswcSupportType getPswcSupport() {
        return pswcSupport;
    }

    /**
     * Définit la valeur de la propriété pswcSupport.
     * 
     * @param value
     *     allowed object is
     *     {@link PswcSupportType }
     *     
     */
    public void setPswcSupport(PswcSupportType value) {
        this.pswcSupport = value;
    }

    /**
     * Obtient la valeur de la propriété amountPatientPartMax.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAmountPatientPartMax() {
        return amountPatientPartMax;
    }

    /**
     * Définit la valeur de la propriété amountPatientPartMax.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAmountPatientPartMax(Integer value) {
        this.amountPatientPartMax = value;
    }

    /**
     * Obtient la valeur de la propriété allowedSupplements.
     * 
     * @return
     *     possible object is
     *     {@link AllowedSupplementsType }
     *     
     */
    public AllowedSupplementsType getAllowedSupplements() {
        return allowedSupplements;
    }

    /**
     * Définit la valeur de la propriété allowedSupplements.
     * 
     * @param value
     *     allowed object is
     *     {@link AllowedSupplementsType }
     *     
     */
    public void setAllowedSupplements(AllowedSupplementsType value) {
        this.allowedSupplements = value;
    }

}
