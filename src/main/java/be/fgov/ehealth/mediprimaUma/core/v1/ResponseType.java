//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.10.29 à 10:32:32 AM CET 
//


package be.fgov.ehealth.mediprimaUma.core.v1;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Basic eHealth SOA Response Type. EHealth Service Responses SHOULD extend from this element.
 * 
 * <p>Classe Java pour ResponseType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="InResponseTo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssueInstant" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseType")
@XmlSeeAlso({
    PaginationResponseType.class,
    StatusResponseType.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ResponseType {

    @XmlAttribute(name = "Id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String id;
    @XmlAttribute(name = "InResponseTo")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String inResponseTo;
    @XmlAttribute(name = "IssueInstant", required = true)
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar issueInstant;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété inResponseTo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getInResponseTo() {
        return inResponseTo;
    }

    /**
     * Définit la valeur de la propriété inResponseTo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setInResponseTo(String value) {
        this.inResponseTo = value;
    }

    /**
     * Obtient la valeur de la propriété issueInstant.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getIssueInstant() {
        return issueInstant;
    }

    /**
     * Définit la valeur de la propriété issueInstant.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIssueInstant(XMLGregorianCalendar value) {
        this.issueInstant = value;
    }

}
