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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.id.v1.IDINSURANCE;


/**
 * <p>Classe Java pour memberinsuranceType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="memberinsuranceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-INSURANCE"/>
 *         &lt;element name="membership" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "memberinsuranceType", propOrder = {
    "id",
    "membership"
})
public class MemberinsuranceType
    implements Serializable
{

    private final static long serialVersionUID = 20150901L;
    @XmlElement(required = true)
    protected IDINSURANCE id;
    @XmlElement(required = true)
    protected Object membership;

    /**
     * Obtient la valeur de la propriété id.
     *
     * @return
     *     possible object is
     *     {@link IDINSURANCE }
     *
     */
    public IDINSURANCE getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     *
     * @param value
     *     allowed object is
     *     {@link IDINSURANCE }
     *
     */
    public void setId(IDINSURANCE value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété membership.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getMembership() {
        return membership;
    }

    /**
     * Définit la valeur de la propriété membership.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setMembership(Object value) {
        this.membership = value;
    }

}
