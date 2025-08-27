
package be.fgov.ehealth.mediprima.core.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PharmaceuticalDrugType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="PharmaceuticalDrugType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v2}MedicalCoverCommonInformationType">
 *       &lt;sequence>
 *         &lt;element name="PharmacyList" type="{urn:be:fgov:ehealth:mediprima:core:v2}NihiiNumberListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PharmaceuticalDrugType", propOrder = {
    "pharmacyList"
})
public class PharmaceuticalDrugType
    extends MedicalCoverCommonInformationType
{

    @XmlElement(name = "PharmacyList")
    protected NihiiNumberListType pharmacyList;

    /**
     * Obtient la valeur de la propriété pharmacyList.
     *
     * @return
     *     possible object is
     *     {@link NihiiNumberListType }
     *
     */
    public NihiiNumberListType getPharmacyList() {
        return pharmacyList;
    }

    /**
     * Définit la valeur de la propriété pharmacyList.
     *
     * @param value
     *     allowed object is
     *     {@link NihiiNumberListType }
     *
     */
    public void setPharmacyList(NihiiNumberListType value) {
        this.pharmacyList = value;
    }

}
