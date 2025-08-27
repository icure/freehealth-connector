
package be.fgov.ehealth.mediprima.protocol.v1;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ZivAmiPatientPartType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="ZivAmiPatientPartType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZivAmiPart" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PatientPart" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZivAmiPatientPartType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
    "zivAmiPart",
    "patientPart"
})
public class ZivAmiPatientPartType {

    @XmlElement(name = "ZivAmiPart", required = true)
    protected BigDecimal zivAmiPart;
    @XmlElement(name = "PatientPart", required = true)
    protected BigDecimal patientPart;

    /**
     * Obtient la valeur de la propriété zivAmiPart.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getZivAmiPart() {
        return zivAmiPart;
    }

    /**
     * Définit la valeur de la propriété zivAmiPart.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setZivAmiPart(BigDecimal value) {
        this.zivAmiPart = value;
    }

    /**
     * Obtient la valeur de la propriété patientPart.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getPatientPart() {
        return patientPart;
    }

    /**
     * Définit la valeur de la propriété patientPart.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPatientPart(BigDecimal value) {
        this.patientPart = value;
    }

}
