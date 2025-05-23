
package be.fgov.ehealth.mediprima.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Support of a CPAS / OCMW
 * 
 * <p>Classe Java pour PswcSupportType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PswcSupportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZivAmiPart" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PatientPart" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Supplement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Convention" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Prescription" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PswcSupportType", propOrder = {
    "zivAmiPart",
    "patientPart",
    "supplement",
    "convention",
    "prescription"
})
public class PswcSupportType {

    @XmlElement(name = "ZivAmiPart", required = true)
    protected String zivAmiPart;
    @XmlElement(name = "PatientPart", required = true)
    protected String patientPart;
    @XmlElement(name = "Supplement", required = true)
    protected String supplement;
    @XmlElement(name = "Convention")
    protected Boolean convention;
    @XmlElement(name = "Prescription")
    protected Boolean prescription;

    /**
     * Obtient la valeur de la propriété zivAmiPart.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZivAmiPart() {
        return zivAmiPart;
    }

    /**
     * Définit la valeur de la propriété zivAmiPart.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZivAmiPart(String value) {
        this.zivAmiPart = value;
    }

    /**
     * Obtient la valeur de la propriété patientPart.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientPart() {
        return patientPart;
    }

    /**
     * Définit la valeur de la propriété patientPart.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientPart(String value) {
        this.patientPart = value;
    }

    /**
     * Obtient la valeur de la propriété supplement.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplement() {
        return supplement;
    }

    /**
     * Définit la valeur de la propriété supplement.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplement(String value) {
        this.supplement = value;
    }

    /**
     * Obtient la valeur de la propriété convention.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConvention() {
        return convention;
    }

    /**
     * Définit la valeur de la propriété convention.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConvention(Boolean value) {
        this.convention = value;
    }

    /**
     * Obtient la valeur de la propriété prescription.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrescription() {
        return prescription;
    }

    /**
     * Définit la valeur de la propriété prescription.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrescription(Boolean value) {
        this.prescription = value;
    }

}
