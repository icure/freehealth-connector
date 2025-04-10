
package be.fgov.ehealth.mediprima.protocol.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CarmedContentCareType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CarmedContentCareType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MedicalCover" type="{urn:be:fgov:ehealth:mediprima:core:v2}MedicalCoverType" minOccurs="0"/>
 *         &lt;element name="RefundPodmiSppis" type="{urn:be:fgov:ehealth:mediprima:core:v2}RefundPodmiSppisType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarmedContentCareType", namespace = "urn:be:fgov:ehealth:mediprima:core:v2", propOrder = {
    "medicalCover",
    "refundPodmiSppis"
})
public class CarmedContentCareType {

    @XmlElement(name = "MedicalCover")
    protected MedicalCoverType medicalCover;
    @XmlElement(name = "RefundPodmiSppis")
    protected RefundPodmiSppisType refundPodmiSppis;

    /**
     * Obtient la valeur de la propriété medicalCover.
     * 
     * @return
     *     possible object is
     *     {@link MedicalCoverType }
     *     
     */
    public MedicalCoverType getMedicalCover() {
        return medicalCover;
    }

    /**
     * Définit la valeur de la propriété medicalCover.
     * 
     * @param value
     *     allowed object is
     *     {@link MedicalCoverType }
     *     
     */
    public void setMedicalCover(MedicalCoverType value) {
        this.medicalCover = value;
    }

    /**
     * Obtient la valeur de la propriété refundPodmiSppis.
     * 
     * @return
     *     possible object is
     *     {@link RefundPodmiSppisType }
     *     
     */
    public RefundPodmiSppisType getRefundPodmiSppis() {
        return refundPodmiSppis;
    }

    /**
     * Définit la valeur de la propriété refundPodmiSppis.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundPodmiSppisType }
     *     
     */
    public void setRefundPodmiSppis(RefundPodmiSppisType value) {
        this.refundPodmiSppis = value;
    }

}
