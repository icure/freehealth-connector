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
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDFORMULARY;


/**
 * <p>Classe Java pour formularyreferenceType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="formularyreferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-FORMULARY" maxOccurs="unbounded"/>
 *           &lt;element name="formularyname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formularyreferenceType", propOrder = {
    "formularyname",
    "cds"
})
@XmlSeeAlso({
    RecipeformularyreferenceType.class
})
public class FormularyreferenceType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    protected String formularyname;
    @XmlElement(name = "cd")
    protected List<CDFORMULARY> cds;

    /**
     * Obtient la valeur de la propriété formularyname.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFormularyname() {
        return formularyname;
    }

    /**
     * Définit la valeur de la propriété formularyname.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFormularyname(String value) {
        this.formularyname = value;
    }

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
     * {@link CDFORMULARY }
     *
     *
     */
    public List<CDFORMULARY> getCds() {
        if (cds == null) {
            cds = new ArrayList<CDFORMULARY>();
        }
        return this.cds;
    }

}
