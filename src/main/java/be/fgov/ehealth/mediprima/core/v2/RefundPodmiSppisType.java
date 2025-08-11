
package be.fgov.ehealth.mediprima.core.v2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Refund Code defined by the POD MI / SPP IS
 * 
 * <p>Classe Java pour RefundPodmiSppisType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RefundPodmiSppisType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RefundCode" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="AffiliedMutualityInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BeneficiaryStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Justification" type="{urn:be:fgov:ehealth:mediprima:core:v2}NameType" maxOccurs="unbounded" minOccurs="2"/>
 *         &lt;element name="PodmiSppisPart" type="{urn:be:fgov:ehealth:mediprima:core:v2}PodmiSppisPartType"/>
 *         &lt;element name="MedicalUrgencyInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefundPodmiSppisType", propOrder = {
    "refundCode",
    "affiliedMutualityInd",
    "beneficiaryStatus",
    "justification",
    "podmiSppisPart",
    "medicalUrgencyInd"
})
public class RefundPodmiSppisType {

    @XmlElement(name = "RefundCode", required = true)
    protected BigInteger refundCode;
    @XmlElement(name = "AffiliedMutualityInd")
    protected boolean affiliedMutualityInd;
    @XmlElement(name = "BeneficiaryStatus", required = true)
    protected String beneficiaryStatus;
    @XmlElement(name = "Justification", required = true)
    protected List<NameType> justification;
    @XmlElement(name = "PodmiSppisPart", required = true)
    protected PodmiSppisPartType podmiSppisPart;
    @XmlElement(name = "MedicalUrgencyInd")
    protected boolean medicalUrgencyInd;

    /**
     * Obtient la valeur de la propriété refundCode.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRefundCode() {
        return refundCode;
    }

    /**
     * Définit la valeur de la propriété refundCode.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRefundCode(BigInteger value) {
        this.refundCode = value;
    }

    /**
     * Obtient la valeur de la propriété affiliedMutualityInd.
     * 
     */
    public boolean isAffiliedMutualityInd() {
        return affiliedMutualityInd;
    }

    /**
     * Définit la valeur de la propriété affiliedMutualityInd.
     * 
     */
    public void setAffiliedMutualityInd(boolean value) {
        this.affiliedMutualityInd = value;
    }

    /**
     * Obtient la valeur de la propriété beneficiaryStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiaryStatus() {
        return beneficiaryStatus;
    }

    /**
     * Définit la valeur de la propriété beneficiaryStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiaryStatus(String value) {
        this.beneficiaryStatus = value;
    }

    /**
     * Gets the value of the justification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the justification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJustification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameType }
     * 
     * 
     */
    public List<NameType> getJustification() {
        if (justification == null) {
            justification = new ArrayList<NameType>();
        }
        return this.justification;
    }

    /**
     * Obtient la valeur de la propriété podmiSppisPart.
     * 
     * @return
     *     possible object is
     *     {@link PodmiSppisPartType }
     *     
     */
    public PodmiSppisPartType getPodmiSppisPart() {
        return podmiSppisPart;
    }

    /**
     * Définit la valeur de la propriété podmiSppisPart.
     * 
     * @param value
     *     allowed object is
     *     {@link PodmiSppisPartType }
     *     
     */
    public void setPodmiSppisPart(PodmiSppisPartType value) {
        this.podmiSppisPart = value;
    }

    /**
     * Obtient la valeur de la propriété medicalUrgencyInd.
     * 
     */
    public boolean isMedicalUrgencyInd() {
        return medicalUrgencyInd;
    }

    /**
     * Définit la valeur de la propriété medicalUrgencyInd.
     * 
     */
    public void setMedicalUrgencyInd(boolean value) {
        this.medicalUrgencyInd = value;
    }

}
