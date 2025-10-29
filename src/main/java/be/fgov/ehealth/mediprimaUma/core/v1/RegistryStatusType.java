//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.10.29 à 10:32:32 AM CET 
//


package be.fgov.ehealth.mediprimaUma.core.v1;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * RegistryStatus will contain extra information about errors or warning
 * 
 * <p>Classe Java pour RegistryStatusType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RegistryStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorDetail" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}ErrorType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistryStatusType", namespace = "urn:be:fgov:ehealth:mediprima:uma:core:v1", propOrder = {
    "errorDetail"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class RegistryStatusType {

    @XmlElement(name = "ErrorDetail", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<ErrorType> errorDetail;

    /**
     * Gets the value of the errorDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:32:32+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<ErrorType> getErrorDetail() {
        if (errorDetail == null) {
            errorDetail = new ArrayList<ErrorType>();
        }
        return this.errorDetail;
    }

}
