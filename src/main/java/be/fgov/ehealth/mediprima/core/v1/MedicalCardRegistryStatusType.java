
package be.fgov.ehealth.mediprima.core.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MedicalCardRegistryStatusType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MedicalCardRegistryStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MedicalCardRegistryMessage" type="{urn:be:fgov:ehealth:mediprima:core:v1}MedicalCardRegistryMessageType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedicalCardRegistryStatusType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
    "medicalCardRegistryMessage"
})
public class MedicalCardRegistryStatusType {

    @XmlElement(name = "MedicalCardRegistryMessage", required = true)
    protected List<MedicalCardRegistryMessageType> medicalCardRegistryMessage;

    /**
     * Gets the value of the medicalCardRegistryMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the medicalCardRegistryMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMedicalCardRegistryMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MedicalCardRegistryMessageType }
     * 
     * 
     */
    public List<MedicalCardRegistryMessageType> getMedicalCardRegistryMessage() {
        if (medicalCardRegistryMessage == null) {
            medicalCardRegistryMessage = new ArrayList<MedicalCardRegistryMessageType>();
        }
        return this.medicalCardRegistryMessage;
    }

}
