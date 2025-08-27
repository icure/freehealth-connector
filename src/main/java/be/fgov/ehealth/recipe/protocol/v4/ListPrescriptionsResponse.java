package be.fgov.ehealth.recipe.protocol.v4;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;


/**
 * <p>Java class for ListPrescriptionsResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ListPrescriptionsResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}StatusResponseType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="SecuredListPrescriptionsResponse" type="{urn:be:fgov:ehealth:recipe:core:v4}SecuredContentType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPrescriptionsResponseType", propOrder = {
        "securedListPrescriptionsResponse"
})
@XmlRootElement(name = "ListPrescriptionsResponse")
public class ListPrescriptionsResponse
        extends StatusResponseType
{

    @XmlElement(name = "SecuredListPrescriptionsResponse")
    protected SecuredContentType securedListPrescriptionsResponse;

    /**
     * Gets the value of the securedListPrescriptionsResponse property.
     *
     * @return
     *     possible object is
     *     {@link SecuredContentType }
     *
     */
    public SecuredContentType getSecuredListPrescriptionsResponse() {
        return securedListPrescriptionsResponse;
    }

    /**
     * Sets the value of the securedListPrescriptionsResponse property.
     *
     * @param value
     *     allowed object is
     *     {@link SecuredContentType }
     *
     */
    public void setSecuredListPrescriptionsResponse(SecuredContentType value) {
        this.securedListPrescriptionsResponse = value;
    }

}
