//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.11.10 à 11:53:46 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

import org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGPRESENTATION;
import org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;


/**
 * <p>Classe Java pour galenicformType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="galenicformType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-DRUG-PRESENTATION" minOccurs="0"/>
 *         &lt;element name="galenicformtext" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "galenicformType", propOrder = {
    "cd",
    "galenicformtext"
})
public class GalenicformType
    implements Serializable
{

    private final static long serialVersionUID = 20150901L;
    protected CDDRUGPRESENTATION cd;
    protected TextType galenicformtext;

    /**
     * Obtient la valeur de la propriété cd.
     *
     * @return
     *     possible object is
     *     {@link CDDRUGPRESENTATION }
     *
     */
    public CDDRUGPRESENTATION getCd() {
        return cd;
    }

    /**
     * Définit la valeur de la propriété cd.
     *
     * @param value
     *     allowed object is
     *     {@link CDDRUGPRESENTATION }
     *
     */
    public void setCd(CDDRUGPRESENTATION value) {
        this.cd = value;
    }

    /**
     * Obtient la valeur de la propriété galenicformtext.
     *
     * @return
     *     possible object is
     *     {@link TextType }
     *
     */
    public TextType getGalenicformtext() {
        return galenicformtext;
    }

    /**
     * Définit la valeur de la propriété galenicformtext.
     *
     * @param value
     *     allowed object is
     *     {@link TextType }
     *
     */
    public void setGalenicformtext(TextType value) {
        this.galenicformtext = value;
    }

}
