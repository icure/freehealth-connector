
package be.fgov.ehealth.mediprimaUma.protocol;

import be.fgov.ehealth.mediprimaUma.core.PeriodType;

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
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SendUrgentMedicalAidAttestationRequestType
    extends AuthorRequestType
{

    @XmlElement(name = "BeneficiarySsin", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String beneficiarySsin;
    @XmlElement(name = "ValidityPeriod", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected PeriodType validityPeriod;
    @XmlElement(name = "MedicalCover", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String medicalCover;

    /**
     * Obtient la valeur de la propriété beneficiarySsin.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setMedicalCover(String value) {
        this.medicalCover = value;
    }

}
