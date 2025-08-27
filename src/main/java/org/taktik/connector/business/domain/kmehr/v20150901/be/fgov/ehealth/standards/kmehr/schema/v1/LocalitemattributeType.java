//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.11.10 à 11:53:46 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.cd.v1.LOCAL;
import org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;


/**
 * <p>Classe Java pour localitemattributeType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="localitemattributeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}LOCAL"/>
 *         &lt;element name="content" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}contentlocalitemattributeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "localitemattributeType", propOrder = {
    "ids",
    "cd",
    "content"
})
public class LocalitemattributeType
    implements Serializable
{

    private final static long serialVersionUID = 20150901L;
    @XmlElement(name = "id", nillable = true)
    protected List<IDKMEHR> ids;
    @XmlElement(required = true, nillable = true)
    protected LOCAL cd;
    @XmlElement(required = true)
    protected ContentlocalitemattributeType content;

    /**
     * Gets the value of the ids property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ids property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIds().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDKMEHR }
     *
     *
     */
    public List<IDKMEHR> getIds() {
        if (ids == null) {
            ids = new ArrayList<IDKMEHR>();
        }
        return this.ids;
    }

    /**
     * Obtient la valeur de la propriété cd.
     *
     * @return
     *     possible object is
     *     {@link LOCAL }
     *
     */
    public LOCAL getCd() {
        return cd;
    }

    /**
     * Définit la valeur de la propriété cd.
     *
     * @param value
     *     allowed object is
     *     {@link LOCAL }
     *
     */
    public void setCd(LOCAL value) {
        this.cd = value;
    }

    /**
     * Obtient la valeur de la propriété content.
     *
     * @return
     *     possible object is
     *     {@link ContentlocalitemattributeType }
     *
     */
    public ContentlocalitemattributeType getContent() {
        return content;
    }

    /**
     * Définit la valeur de la propriété content.
     *
     * @param value
     *     allowed object is
     *     {@link ContentlocalitemattributeType }
     *
     */
    public void setContent(ContentlocalitemattributeType value) {
        this.content = value;
    }

}
