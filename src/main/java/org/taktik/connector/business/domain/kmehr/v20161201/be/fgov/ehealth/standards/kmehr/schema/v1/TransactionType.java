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
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.LnkType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;


/**
 * a transaction is a set of medical information validated by one healthcare professional at one given moment.
 *
 * <p>Classe Java pour transactionType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="transactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confidentiality" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}confidentialityType" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHR" maxOccurs="unbounded"/>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-TRANSACTION" maxOccurs="unbounded"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="author" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}authorType"/>
 *         &lt;element name="redactor" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}authorType" minOccurs="0"/>
 *         &lt;element name="iscomplete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isvalidated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="expirationdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="heading" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}headingType"/>
 *           &lt;element name="item" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}itemType"/>
 *           &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
 *           &lt;element name="text-with-layout" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}textWithLayoutType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="lnk" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}lnkType"/>
 *         &lt;/choice>
 *         &lt;element name="recorddatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionType", propOrder = {
    "confidentiality",
    "ids",
    "cds",
    "date",
    "time",
    "author",
    "redactor",
    "iscomplete",
    "isvalidated",
    "expirationdate",
    "headingsAndItemsAndTexts",
    "recorddatetime",
    "version"
})
public class TransactionType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    protected ConfidentialityType confidentiality;
    @XmlElement(name = "id", required = true)
    protected List<IDKMEHR> ids;
    @XmlElement(name = "cd", required = true)
    protected List<CDTRANSACTION> cds;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar time;
    @XmlElement(required = true)
    protected AuthorType author;
    protected AuthorType redactor;
    protected boolean iscomplete;
    protected boolean isvalidated;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationdate;
    @XmlElements({
        @XmlElement(name = "heading", type = HeadingType.class),
        @XmlElement(name = "item", type = ItemType.class),
        @XmlElement(name = "text", type = TextType.class),
        @XmlElement(name = "text-with-layout", type = TextWithLayoutType.class, nillable = true),
        @XmlElement(name = "lnk", type = LnkType.class)
    })
    protected List<Serializable> headingsAndItemsAndTexts;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar recorddatetime;
    protected String version;

    /**
     * Obtient la valeur de la propriété confidentiality.
     *
     * @return
     *     possible object is
     *     {@link ConfidentialityType }
     *
     */
    public ConfidentialityType getConfidentiality() {
        return confidentiality;
    }

    /**
     * Définit la valeur de la propriété confidentiality.
     *
     * @param value
     *     allowed object is
     *     {@link ConfidentialityType }
     *
     */
    public void setConfidentiality(ConfidentialityType value) {
        this.confidentiality = value;
    }

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
     * {@link CDTRANSACTION }
     *
     *
     */
    public List<CDTRANSACTION> getCds() {
        if (cds == null) {
            cds = new ArrayList<CDTRANSACTION>();
        }
        return this.cds;
    }

    /**
     * Obtient la valeur de la propriété date.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété time.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Définit la valeur de la propriété time.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Obtient la valeur de la propriété author.
     *
     * @return
     *     possible object is
     *     {@link AuthorType }
     *
     */
    public AuthorType getAuthor() {
        return author;
    }

    /**
     * Définit la valeur de la propriété author.
     *
     * @param value
     *     allowed object is
     *     {@link AuthorType }
     *
     */
    public void setAuthor(AuthorType value) {
        this.author = value;
    }

    /**
     * Obtient la valeur de la propriété redactor.
     *
     * @return
     *     possible object is
     *     {@link AuthorType }
     *
     */
    public AuthorType getRedactor() {
        return redactor;
    }

    /**
     * Définit la valeur de la propriété redactor.
     *
     * @param value
     *     allowed object is
     *     {@link AuthorType }
     *
     */
    public void setRedactor(AuthorType value) {
        this.redactor = value;
    }

    /**
     * Obtient la valeur de la propriété iscomplete.
     *
     */
    public boolean isIscomplete() {
        return iscomplete;
    }

    /**
     * Définit la valeur de la propriété iscomplete.
     *
     */
    public void setIscomplete(boolean value) {
        this.iscomplete = value;
    }

    /**
     * Obtient la valeur de la propriété isvalidated.
     *
     */
    public boolean isIsvalidated() {
        return isvalidated;
    }

    /**
     * Définit la valeur de la propriété isvalidated.
     *
     */
    public void setIsvalidated(boolean value) {
        this.isvalidated = value;
    }

    /**
     * Obtient la valeur de la propriété expirationdate.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getExpirationdate() {
        return expirationdate;
    }

    /**
     * Définit la valeur de la propriété expirationdate.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setExpirationdate(XMLGregorianCalendar value) {
        this.expirationdate = value;
    }

    /**
     * Gets the value of the headingsAndItemsAndTexts property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the headingsAndItemsAndTexts property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeadingsAndItemsAndTexts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HeadingType }
     * {@link ItemType }
     * {@link TextType }
     * {@link TextWithLayoutType }
     * {@link LnkType }
     *
     *
     */
    public List<Serializable> getHeadingsAndItemsAndTexts() {
        if (headingsAndItemsAndTexts == null) {
            headingsAndItemsAndTexts = new ArrayList<Serializable>();
        }
        return this.headingsAndItemsAndTexts;
    }

    /**
     * Obtient la valeur de la propriété recorddatetime.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getRecorddatetime() {
        return recorddatetime;
    }

    /**
     * Définit la valeur de la propriété recorddatetime.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setRecorddatetime(XMLGregorianCalendar value) {
        this.recorddatetime = value;
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
