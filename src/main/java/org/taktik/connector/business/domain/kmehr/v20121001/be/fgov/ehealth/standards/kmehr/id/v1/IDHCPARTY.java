//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:06 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * <p>Classe Java pour ID-HCPARTY complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="ID-HCPARTY">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="S" use="required" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-HCPARTYschemes" />
 *       &lt;attribute name="SV" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ID-HCPARTY", propOrder = {
    "value"
})
public class IDHCPARTY
    implements Serializable
{

    private final static long serialVersionUID = 20121001L;
    @XmlValue
    protected String value;
    @XmlAttribute(name = "S", required = true)
    protected IDHCPARTYschemes s;
    @XmlAttribute(name = "SV", required = true)
    protected String sv = "1.0";
    @XmlAttribute(name = "SL")
    protected String sl;

    /**
     * Obtient la valeur de la propriété value.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété s.
     *
     * @return
     *     possible object is
     *     {@link IDHCPARTYschemes }
     *
     */
    public IDHCPARTYschemes getS() {
        return s;
    }

    /**
     * Définit la valeur de la propriété s.
     *
     * @param value
     *     allowed object is
     *     {@link IDHCPARTYschemes }
     *
     */
    public void setS(IDHCPARTYschemes value) {
        this.s = value;
    }

    /**
     * Obtient la valeur de la propriété sv.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSV() {
        return sv;
    }

    /**
     * Définit la valeur de la propriété sv.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSV(String value) {
        this.sv = value;
    }

    /**
     * Obtient la valeur de la propriété sl.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSL() {
        return sl;
    }

    /**
     * Définit la valeur de la propriété sl.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSL(String value) {
        this.sl = value;
    }

}
