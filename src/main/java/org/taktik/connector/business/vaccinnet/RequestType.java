
package org.taktik.connector.business.vaccinnet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Each request contains the same base information
 *
 * <p>Java class for RequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SoftwareId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VaccinnetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestType", propOrder = {
    "softwareId",
    "vaccinnetId"
})
@XmlSeeAlso({
    AddVaccinationsRequestType.class,
    GetVaccinationsRequestType.class,
    RemoveVaccinationRequestType.class
})
public class RequestType {

    @XmlElement(name = "SoftwareId", required = true)
    protected String softwareId;
    @XmlElement(name = "VaccinnetId")
    protected String vaccinnetId;

    /**
     * Gets the value of the softwareId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSoftwareId() {
        return softwareId;
    }

    /**
     * Sets the value of the softwareId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSoftwareId(String value) {
        this.softwareId = value;
    }

    /**
     * Gets the value of the vaccinnetId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVaccinnetId() {
        return vaccinnetId;
    }

    /**
     * Sets the value of the vaccinnetId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVaccinnetId(String value) {
        this.vaccinnetId = value;
    }

}
