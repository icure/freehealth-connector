//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:06 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1.CDINCAPACITY;


/**
 * <p>Classe Java pour incapacityType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="incapacityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-INCAPACITY" maxOccurs="unbounded"/>
 *         &lt;element name="incapacityreason" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}incapacityreasonType" minOccurs="0"/>
 *         &lt;element name="percentage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="outofhomeallowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "incapacityType", propOrder = {
    "cds",
    "incapacityreason",
    "percentage",
    "outofhomeallowed"
})
public class IncapacityType
    implements Serializable
{

    private final static long serialVersionUID = 20121001L;
    @XmlElement(name = "cd", required = true)
    protected List<CDINCAPACITY> cds;
    protected IncapacityreasonType incapacityreason;
    protected BigDecimal percentage;
    protected Boolean outofhomeallowed;

    /**
     * Gets the value of the cds property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cds property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCds().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CDINCAPACITY }
     *
     *
     */
    public List<CDINCAPACITY> getCds() {
        if (cds == null) {
            cds = new ArrayList<CDINCAPACITY>();
        }
        return this.cds;
    }

    /**
     * Obtient la valeur de la propriété incapacityreason.
     *
     * @return
     *     possible object is
     *     {@link IncapacityreasonType }
     *
     */
    public IncapacityreasonType getIncapacityreason() {
        return incapacityreason;
    }

    /**
     * Définit la valeur de la propriété incapacityreason.
     *
     * @param value
     *     allowed object is
     *     {@link IncapacityreasonType }
     *
     */
    public void setIncapacityreason(IncapacityreasonType value) {
        this.incapacityreason = value;
    }

    /**
     * Obtient la valeur de la propriété percentage.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getPercentage() {
        return percentage;
    }

    /**
     * Définit la valeur de la propriété percentage.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPercentage(BigDecimal value) {
        this.percentage = value;
    }

    /**
     * Obtient la valeur de la propriété outofhomeallowed.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isOutofhomeallowed() {
        return outofhomeallowed;
    }

    /**
     * Définit la valeur de la propriété outofhomeallowed.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setOutofhomeallowed(Boolean value) {
        this.outofhomeallowed = value;
    }

}
