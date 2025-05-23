
package be.fgov.ehealth.mediprima.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MedicalTransportationType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MedicalTransportationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v2}MedicalCoverCommonInformationType">
 *       &lt;sequence>
 *         &lt;element name="CompanyList" type="{urn:be:fgov:ehealth:mediprima:core:v2}CompanyListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedicalTransportationType", propOrder = {
    "companyList"
})
public class MedicalTransportationType
    extends MedicalCoverCommonInformationType
{

    @XmlElement(name = "CompanyList")
    protected CompanyListType companyList;

    /**
     * Obtient la valeur de la propriété companyList.
     * 
     * @return
     *     possible object is
     *     {@link CompanyListType }
     *     
     */
    public CompanyListType getCompanyList() {
        return companyList;
    }

    /**
     * Définit la valeur de la propriété companyList.
     * 
     * @param value
     *     allowed object is
     *     {@link CompanyListType }
     *     
     */
    public void setCompanyList(CompanyListType value) {
        this.companyList = value;
    }

}
