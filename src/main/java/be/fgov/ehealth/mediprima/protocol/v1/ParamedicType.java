
package be.fgov.ehealth.mediprima.protocol.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ParamedicType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ParamedicType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v1}MedicalCoverCommonInformationType">
 *       &lt;sequence>
 *         &lt;element name="ProviderList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v1}NihiiNumberListType">
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParamedicType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
    "providerList"
})
public class ParamedicType
    extends MedicalCoverCommonInformationType
{

    @XmlElement(name = "ProviderList")
    protected ParamedicType.ProviderList providerList;

    /**
     * Obtient la valeur de la propriété providerList.
     * 
     * @return
     *     possible object is
     *     {@link ParamedicType.ProviderList }
     *     
     */
    public ParamedicType.ProviderList getProviderList() {
        return providerList;
    }

    /**
     * Définit la valeur de la propriété providerList.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamedicType.ProviderList }
     *     
     */
    public void setProviderList(ParamedicType.ProviderList value) {
        this.providerList = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{urn:be:fgov:ehealth:mediprima:core:v1}NihiiNumberListType">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ProviderList
        extends NihiiNumberListType
    {


    }

}
