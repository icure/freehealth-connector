
package be.fgov.ehealth.mediprima.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour BySsinType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BySsinType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ssin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="Period" type="{urn:be:fgov:ehealth:mediprima:core:v2}PeriodType"/>
 *           &lt;element name="ReferenceDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
@XmlType(name = "BySsinType", propOrder = {
    "ssin",
    "period",
    "referenceDate"
})
public class BySsinType {

    @XmlElement(name = "Ssin", required = true)
    protected String ssin;
    @XmlElement(name = "Period")
    protected PeriodType period;
    @XmlElement(name = "ReferenceDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar referenceDate;

    /**
     * Obtient la valeur de la propriété ssin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsin() {
        return ssin;
    }

    /**
     * Définit la valeur de la propriété ssin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsin(String value) {
        this.ssin = value;
    }

    /**
     * Obtient la valeur de la propriété period.
     * 
     * @return
     *     possible object is
     *     {@link PeriodType }
     *     
     */
    public PeriodType getPeriod() {
        return period;
    }

    /**
     * Définit la valeur de la propriété period.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodType }
     *     
     */
    public void setPeriod(PeriodType value) {
        this.period = value;
    }

    /**
     * Obtient la valeur de la propriété referenceDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReferenceDate() {
        return referenceDate;
    }

    /**
     * Définit la valeur de la propriété referenceDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReferenceDate(XMLGregorianCalendar value) {
        this.referenceDate = value;
    }

}
