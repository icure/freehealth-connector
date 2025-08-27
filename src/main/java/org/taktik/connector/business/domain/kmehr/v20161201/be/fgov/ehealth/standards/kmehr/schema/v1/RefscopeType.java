//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDREFSCOPE;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;


/**
 * to specify if the minref and maxref references are adjusted to the patient's age, sex, ...,
 *
 * <p>Classe Java pour refscopeType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="refscopeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-REFSCOPE"/>
 *         &lt;element name="refvalue" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="minref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}minrefType" minOccurs="0"/>
 *                   &lt;element name="maxref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}maxrefType" minOccurs="0"/>
 *                   &lt;element name="context" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
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
@XmlType(name = "refscopeType", propOrder = {
    "cd",
    "refvalues"
})
public class RefscopeType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(required = true)
    protected CDREFSCOPE cd;
    @XmlElement(name = "refvalue")
    protected List<RefscopeType.Refvalue> refvalues;

    /**
     * Obtient la valeur de la propriété cd.
     *
     * @return
     *     possible object is
     *     {@link CDREFSCOPE }
     *
     */
    public CDREFSCOPE getCd() {
        return cd;
    }

    /**
     * Définit la valeur de la propriété cd.
     *
     * @param value
     *     allowed object is
     *     {@link CDREFSCOPE }
     *
     */
    public void setCd(CDREFSCOPE value) {
        this.cd = value;
    }

    /**
     * Gets the value of the refvalues property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refvalues property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefvalues().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefscopeType.Refvalue }
     *
     *
     */
    public List<RefscopeType.Refvalue> getRefvalues() {
        if (refvalues == null) {
            refvalues = new ArrayList<RefscopeType.Refvalue>();
        }
        return this.refvalues;
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
     *         &lt;element name="minref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}minrefType" minOccurs="0"/>
     *         &lt;element name="maxref" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}maxrefType" minOccurs="0"/>
     *         &lt;element name="context" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
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
        "minref",
        "maxref",
        "context"
    })
    public static class Refvalue
        implements Serializable
    {

        private final static long serialVersionUID = 20161201L;
        protected MinrefType minref;
        protected MaxrefType maxref;
        @XmlElement(required = true)
        protected TextType context;

        /**
         * Obtient la valeur de la propriété minref.
         *
         * @return
         *     possible object is
         *     {@link MinrefType }
         *
         */
        public MinrefType getMinref() {
            return minref;
        }

        /**
         * Définit la valeur de la propriété minref.
         *
         * @param value
         *     allowed object is
         *     {@link MinrefType }
         *
         */
        public void setMinref(MinrefType value) {
            this.minref = value;
        }

        /**
         * Obtient la valeur de la propriété maxref.
         *
         * @return
         *     possible object is
         *     {@link MaxrefType }
         *
         */
        public MaxrefType getMaxref() {
            return maxref;
        }

        /**
         * Définit la valeur de la propriété maxref.
         *
         * @param value
         *     allowed object is
         *     {@link MaxrefType }
         *
         */
        public void setMaxref(MaxrefType value) {
            this.maxref = value;
        }

        /**
         * Obtient la valeur de la propriété context.
         *
         * @return
         *     possible object is
         *     {@link TextType }
         *
         */
        public TextType getContext() {
            return context;
        }

        /**
         * Définit la valeur de la propriété context.
         *
         * @param value
         *     allowed object is
         *     {@link TextType }
         *
         */
        public void setContext(TextType value) {
            this.context = value;
        }

    }

}
