
package be.fgov.ehealth.mediprima.protocol.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DoctorType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="DoctorType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v1}MedicalCoverCommonInformationType">
 *       &lt;sequence>
 *         &lt;element name="HealthCareProviderList" type="{urn:be:fgov:ehealth:mediprima:core:v1}NihiiNumberListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoctorType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
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
