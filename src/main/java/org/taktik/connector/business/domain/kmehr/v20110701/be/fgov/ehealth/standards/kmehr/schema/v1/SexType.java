//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:47:59 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEX;


/**
 * <p>Classe Java pour sexType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="sexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-SEX"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sexType", propOrder = {
    "cd"
})
public class SexType
    implements Serializable
{

    private final static long serialVersionUID = 20110701L;
    @XmlElement(required = true)
    protected CDSEX cd;

    /**
     * Obtient la valeur de la propriété cd.
     *
     * @return
     *     possible object is
     *     {@link CDSEX }
     *
     */
    public CDSEX getCd() {
        return cd;
    }

    /**
     * Définit la valeur de la propriété cd.
     *
     * @param value
     *     allowed object is
     *     {@link CDSEX }
     *
     */
    public void setCd(CDSEX value) {
        this.cd = value;
    }

}
