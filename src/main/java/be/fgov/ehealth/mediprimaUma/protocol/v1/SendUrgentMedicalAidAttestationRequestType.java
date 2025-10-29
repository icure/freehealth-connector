//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2025.10.29 à 10:42:52 AM CET
//


package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.AuthorRequestType;
import be.fgov.ehealth.mediprimaUma.core.v1.PeriodType;

import javax.annotation.Generated;
import javax.xml.bind.annotation.*;


/**
 * Send urgent medical aid attestation request
 *
 * <p>Classe Java pour SendUrgentMedicalAidAttestationRequestType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="SendUrgentMedicalAidAttestationRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}AuthorRequestType">
 *       &lt;sequence>
 *         &lt;element name="BeneficiarySsin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValidityPeriod" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}PeriodType"/>
 *         &lt;element name="MedicalCover" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}MedicalCoverType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(
        name = "SendUrgentMedicalAidAttestationRequest"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SendUrgentMedicalAidAttestationRequestType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "beneficiarySsin",
    "validityPeriod",
    "medicalCover"
})
public class SendUrgentMedicalAidAttestationRequestType
    extends AuthorRequestType
{

    @XmlElement(name = "BeneficiarySsin", required = true)
    protected String beneficiarySsin;
    @XmlElement(name = "ValidityPeriod", required = true)
    protected PeriodType validityPeriod;
    @XmlElement(name = "MedicalCover", required = true)
    protected String medicalCover;

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

}
