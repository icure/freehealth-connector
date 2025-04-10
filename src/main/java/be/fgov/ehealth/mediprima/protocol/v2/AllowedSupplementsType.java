
package be.fgov.ehealth.mediprima.protocol.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Description of the supplements paid by the OCMW / CPAS (can be filled only for hospitalization medical cover and ambulatory hospitalization medical cover) : maximum amount allowed, supplement types list.
 * 
 * <p>Classe Java pour AllowedSupplementsType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AllowedSupplementsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AmountMaxAllowed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SupplementTypeList" type="{urn:be:fgov:ehealth:mediprima:core:v2}SupplementTypeListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllowedSupplementsType", namespace = "urn:be:fgov:ehealth:mediprima:core:v2", propOrder = {
    "amountMaxAllowed",
    "supplementTypeList"
})
public class AllowedSupplementsType {

    @XmlElement(name = "AmountMaxAllowed")
    protected Integer amountMaxAllowed;
    @XmlElement(name = "SupplementTypeList")
    protected SupplementTypeListType supplementTypeList;

    /**
     * Obtient la valeur de la propriété amountMaxAllowed.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAmountMaxAllowed() {
        return amountMaxAllowed;
    }

    /**
     * Définit la valeur de la propriété amountMaxAllowed.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAmountMaxAllowed(Integer value) {
        this.amountMaxAllowed = value;
    }

    /**
     * Obtient la valeur de la propriété supplementTypeList.
     * 
     * @return
     *     possible object is
     *     {@link SupplementTypeListType }
     *     
     */
    public SupplementTypeListType getSupplementTypeList() {
        return supplementTypeList;
    }

    /**
     * Définit la valeur de la propriété supplementTypeList.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplementTypeListType }
     *     
     */
    public void setSupplementTypeList(SupplementTypeListType value) {
        this.supplementTypeList = value;
    }

}
