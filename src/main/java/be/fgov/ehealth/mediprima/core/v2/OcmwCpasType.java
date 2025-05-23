
package be.fgov.ehealth.mediprima.core.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour OcmwCpasType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OcmwCpasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CbeNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MunicipalityIns" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{urn:be:fgov:ehealth:mediprima:core:v2}NameType" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OcmwCpasType", propOrder = {
    "cbeNumber",
    "municipalityIns",
    "name"
})
public class OcmwCpasType {

    @XmlElement(name = "CbeNumber", required = true)
    protected String cbeNumber;
    @XmlElement(name = "MunicipalityIns", required = true)
    protected String municipalityIns;
    @XmlElement(name = "Name", required = true)
    protected List<NameType> name;

    /**
     * Obtient la valeur de la propriété cbeNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbeNumber() {
        return cbeNumber;
    }

    /**
     * Définit la valeur de la propriété cbeNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbeNumber(String value) {
        this.cbeNumber = value;
    }

    /**
     * Obtient la valeur de la propriété municipalityIns.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityIns() {
        return municipalityIns;
    }

    /**
     * Définit la valeur de la propriété municipalityIns.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityIns(String value) {
        this.municipalityIns = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameType }
     * 
     * 
     */
    public List<NameType> getName() {
        if (name == null) {
            name = new ArrayList<NameType>();
        }
        return this.name;
    }

}
