
package be.fgov.ehealth.mediprima.core.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PodmiSppisPartType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="PodmiSppisPartType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HospitalizationPart" type="{urn:be:fgov:ehealth:mediprima:core:v2}ZivAmiPatientPartType"/>
 *         &lt;element name="AmbulatoryCarePart" type="{urn:be:fgov:ehealth:mediprima:core:v2}ZivAmiPatientPartType"/>
 *         &lt;element name="OtherPart" type="{urn:be:fgov:ehealth:mediprima:core:v2}ZivAmiPatientPartType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PodmiSppisPartType", propOrder = {
    "hospitalizationPart",
    "ambulatoryCarePart",
    "otherPart"
})
public class PodmiSppisPartType {

    @XmlElement(name = "HospitalizationPart", required = true)
    protected ZivAmiPatientPartType hospitalizationPart;
    @XmlElement(name = "AmbulatoryCarePart", required = true)
    protected ZivAmiPatientPartType ambulatoryCarePart;
    @XmlElement(name = "OtherPart", required = true)
    protected ZivAmiPatientPartType otherPart;

    /**
     * Obtient la valeur de la propriété hospitalizationPart.
     *
     * @return
     *     possible object is
     *     {@link ZivAmiPatientPartType }
     *
     */
    public ZivAmiPatientPartType getHospitalizationPart() {
        return hospitalizationPart;
    }

    /**
     * Définit la valeur de la propriété hospitalizationPart.
     *
     * @param value
     *     allowed object is
     *     {@link ZivAmiPatientPartType }
     *
     */
    public void setHospitalizationPart(ZivAmiPatientPartType value) {
        this.hospitalizationPart = value;
    }

    /**
     * Obtient la valeur de la propriété ambulatoryCarePart.
     *
     * @return
     *     possible object is
     *     {@link ZivAmiPatientPartType }
     *
     */
    public ZivAmiPatientPartType getAmbulatoryCarePart() {
        return ambulatoryCarePart;
    }

    /**
     * Définit la valeur de la propriété ambulatoryCarePart.
     *
     * @param value
     *     allowed object is
     *     {@link ZivAmiPatientPartType }
     *
     */
    public void setAmbulatoryCarePart(ZivAmiPatientPartType value) {
        this.ambulatoryCarePart = value;
    }

    /**
     * Obtient la valeur de la propriété otherPart.
     *
     * @return
     *     possible object is
     *     {@link ZivAmiPatientPartType }
     *
     */
    public ZivAmiPatientPartType getOtherPart() {
        return otherPart;
    }

    /**
     * Définit la valeur de la propriété otherPart.
     *
     * @param value
     *     allowed object is
     *     {@link ZivAmiPatientPartType }
     *
     */
    public void setOtherPart(ZivAmiPatientPartType value) {
        this.otherPart = value;
    }

}
