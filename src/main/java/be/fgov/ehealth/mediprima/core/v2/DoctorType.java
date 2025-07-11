
package be.fgov.ehealth.mediprima.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DoctorType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DoctorType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v2}MedicalCoverCommonInformationType">
 *       &lt;sequence>
 *         &lt;element name="HealthCareProviderList" type="{urn:be:fgov:ehealth:mediprima:core:v2}NihiiNumberListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoctorType", propOrder = {
    "healthCareProviderList"
})
public class DoctorType
    extends MedicalCoverCommonInformationType
{

    @XmlElement(name = "HealthCareProviderList")
    protected NihiiNumberListType healthCareProviderList;

    /**
     * Obtient la valeur de la propriété healthCareProviderList.
     * 
     * @return
     *     possible object is
     *     {@link NihiiNumberListType }
     *     
     */
    public NihiiNumberListType getHealthCareProviderList() {
        return healthCareProviderList;
    }

    /**
     * Définit la valeur de la propriété healthCareProviderList.
     * 
     * @param value
     *     allowed object is
     *     {@link NihiiNumberListType }
     *     
     */
    public void setHealthCareProviderList(NihiiNumberListType value) {
        this.healthCareProviderList = value;
    }

}
