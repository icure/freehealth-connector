//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2025.10.29 à 10:42:52 AM CET
//


package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.AttestationType;
import be.fgov.ehealth.mediprimaUma.core.v1.StatusResponseType;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.*;


/**
 * Search urgent medical aid attestation result(s)
 *
 * <p>Classe Java pour SearchUrgentMedicalAidAttestationResponseType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="SearchUrgentMedicalAidAttestationResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}StatusResponseType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Attestation" type="{urn:be:fgov:ehealth:mediprima:uma:core:v1}AttestationType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(
        name = "SearchUrgentMedicalAidAttestationResponse"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchUrgentMedicalAidAttestationResponseType", namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", propOrder = {
    "attestation"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:42:52+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SearchUrgentMedicalAidAttestationResponseType
    extends StatusResponseType
{

    @XmlElement(name = "Attestation")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:42:52+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<AttestationType> attestation;

    /**
     * Gets the value of the attestation property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attestation property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttestation().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttestationType }
     *
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-29T10:42:52+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<AttestationType> getAttestation() {
        if (attestation == null) {
            attestation = new ArrayList<AttestationType>();
        }
        return this.attestation;
    }

}
