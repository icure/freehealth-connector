//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:09 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour timequantityType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="timequantityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="decimal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="unit" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}timeunitType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timequantityType", propOrder = {
    "decimal",
    "unit"
})
public class TimequantityType
    implements Serializable
{

    private final static long serialVersionUID = 20130710L;
    @XmlElement(required = true)
    protected BigDecimal decimal;
    protected TimeunitType unit;

    /**
     * Obtient la valeur de la propriété decimal.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getDecimal() {
        return decimal;
    }

    /**
     * Définit la valeur de la propriété decimal.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setDecimal(BigDecimal value) {
        this.decimal = value;
    }

    /**
     * Obtient la valeur de la propriété unit.
     *
     * @return
     *     possible object is
     *     {@link TimeunitType }
     *
     */
    public TimeunitType getUnit() {
        return unit;
    }

    /**
     * Définit la valeur de la propriété unit.
     *
     * @param value
     *     allowed object is
     *     {@link TimeunitType }
     *
     */
    public void setUnit(TimeunitType value) {
        this.unit = value;
    }

}
