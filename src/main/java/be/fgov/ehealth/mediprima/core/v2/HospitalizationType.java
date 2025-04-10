
package be.fgov.ehealth.mediprima.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour HospitalizationType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HospitalizationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v2}MedicalCoverCommonInformationType">
 *       &lt;sequence>
 *         &lt;element name="HospitalList" type="{urn:be:fgov:ehealth:mediprima:core:v2}NihiiNumberListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HospitalizationType", namespace = "urn:be:fgov:ehealth:mediprima:core:v2", propOrder = {
    "hospitalList"
})
public class HospitalizationType
    extends MedicalCoverCommonInformationType
{

    @XmlElement(name = "HospitalList")
    protected NihiiNumberListType hospitalList;

    /**
     * Obtient la valeur de la propriété hospitalList.
     * 
     * @return
     *     possible object is
     *     {@link NihiiNumberListType }
     *     
     */
    public NihiiNumberListType getHospitalList() {
        return hospitalList;
    }

    /**
     * Définit la valeur de la propriété hospitalList.
     * 
     * @param value
     *     allowed object is
     *     {@link NihiiNumberListType }
     *     
     */
    public void setHospitalList(NihiiNumberListType value) {
        this.hospitalList = value;
    }

}
