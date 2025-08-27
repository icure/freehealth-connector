//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:09 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.taktik.connector.business.domain.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;


/**
 *
 *           a magistral preparation can be prescribed as a (coded)
 *           reference to a preparation in a reference book (formularyreference), or as a
 *           (coded) list of individual compounds (compoundlist), or as free text
 *           (magistraltext)
 *
 *
 * <p>Classe Java pour compoundprescriptionType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="compoundprescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;choice>
 *           &lt;element name="compound" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}compoundType" maxOccurs="unbounded"/>
 *           &lt;element name="formularyreference" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}formularyreferenceType"/>
 *           &lt;element name="magistraltext" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
 *         &lt;/choice>
 *         &lt;element name="galenicform" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}galenicformType" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}quantityType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="L" type="{http://www.w3.org/2001/XMLSchema}language" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compoundprescriptionType", propOrder = {
    "content"
})
public class CompoundprescriptionType
    implements Serializable
{

    private final static long serialVersionUID = 20130710L;
    @XmlElementRefs({
        @XmlElementRef(name = "formularyreference", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "galenicform", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "quantity", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "compound", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "magistraltext", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "L")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String l;

    /**
     *
     *           a magistral preparation can be prescribed as a (coded)
     *           reference to a preparation in a reference book (formularyreference), or as a
     *           (coded) list of individual compounds (compoundlist), or as free text
     *           (magistraltext)
     *           Gets the value of the content property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CompoundType }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link TextType }{@code >}
     * {@link JAXBElement }{@code <}{@link FormularyreferenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link GalenicformType }{@code >}
     * {@link JAXBElement }{@code <}{@link QuantityType }{@code >}
     *
     *
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

    /**
     * Obtient la valeur de la propriété l.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getL() {
        return l;
    }

    /**
     * Définit la valeur de la propriété l.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setL(String value) {
        this.l = value;
    }

}
