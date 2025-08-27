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

import org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1.CDMESSAGE;
import org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1.CDSTANDARD;


/**
 * to specify the version of the kmehr specification to which this message complies
 *
 * <p>Classe Java pour standardType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="standardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-STANDARD"/>
 *         &lt;element name="specialisation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-MESSAGE"/>
 *                   &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "standardType", propOrder = {
    "cd",
    "specialisation"
})
public class StandardType
    implements Serializable
{

    private final static long serialVersionUID = 20110701L;
    @XmlElement(required = true)
    protected CDSTANDARD cd;
    protected StandardType.Specialisation specialisation;

    /**
     * Obtient la valeur de la propriété cd.
     *
     * @return
     *     possible object is
     *     {@link CDSTANDARD }
     *
     */
    public CDSTANDARD getCd() {
        return cd;
    }

    /**
     * Définit la valeur de la propriété cd.
     *
     * @param value
     *     allowed object is
     *     {@link CDSTANDARD }
     *
     */
    public void setCd(CDSTANDARD value) {
        this.cd = value;
    }

    /**
     * Obtient la valeur de la propriété specialisation.
     *
     * @return
     *     possible object is
     *     {@link StandardType.Specialisation }
     *
     */
    public StandardType.Specialisation getSpecialisation() {
        return specialisation;
    }

    /**
     * Définit la valeur de la propriété specialisation.
     *
     * @param value
     *     allowed object is
     *     {@link StandardType.Specialisation }
     *
     */
    public void setSpecialisation(StandardType.Specialisation value) {
        this.specialisation = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     *
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-MESSAGE"/>
     *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "cd",
        "version"
    })
    public static class Specialisation
        implements Serializable
    {

        private final static long serialVersionUID = 20110701L;
        @XmlElement(required = true)
        protected CDMESSAGE cd;
        @XmlElement(required = true)
        protected String version;

        /**
         * Obtient la valeur de la propriété cd.
         *
         * @return
         *     possible object is
         *     {@link CDMESSAGE }
         *
         */
        public CDMESSAGE getCd() {
            return cd;
        }

        /**
         * Définit la valeur de la propriété cd.
         *
         * @param value
         *     allowed object is
         *     {@link CDMESSAGE }
         *
         */
        public void setCd(CDMESSAGE value) {
            this.cd = value;
        }

        /**
         * Obtient la valeur de la propriété version.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVersion() {
            return version;
        }

        /**
         * Définit la valeur de la propriété version.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVersion(String value) {
            this.version = value;
        }

    }

}
