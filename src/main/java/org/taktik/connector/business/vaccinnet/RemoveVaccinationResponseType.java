
package org.taktik.connector.business.vaccinnet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Service needed to remove a vaccination from vaccinnet.
 *
 * <p>Java class for RemoveVaccinationResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RemoveVaccinationResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21/message}StatusType"/>
 *         &lt;sequence>
 *           &lt;element name="PatientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="VaccinationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "RemoveVaccinationResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoveVaccinationResponseType", propOrder = {
    "status",
    "patientId",
    "vaccinationId"
})
public class RemoveVaccinationResponseType {

    @XmlElement(name = "Status", required = true)
    protected VaccinationStatusType status;
    @XmlElement(name = "PatientId")
    protected String patientId;
    @XmlElement(name = "VaccinationId")
    protected String vaccinationId;

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link VaccinationStatusType }
     *
     */
    public VaccinationStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link VaccinationStatusType }
     *
     */
    public void setStatus(VaccinationStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the patientId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the value of the patientId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPatientId(String value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the vaccinationId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVaccinationId() {
        return vaccinationId;
    }

    /**
     * Sets the value of the vaccinationId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVaccinationId(String value) {
        this.vaccinationId = value;
    }

}
