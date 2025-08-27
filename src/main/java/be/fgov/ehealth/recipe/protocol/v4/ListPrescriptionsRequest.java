package be.fgov.ehealth.recipe.protocol.v4;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;


/**
 * <p>Java class for ListPrescriptionsRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ListPrescriptionsRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:recipe:protocol:v4}RequestType">
 *       &lt;sequence>
 *         &lt;element name="SecuredListPrescriptionsRequest" type="{urn:be:fgov:ehealth:recipe:core:v4}SecuredContentType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPrescriptionsRequestType", propOrder = {
        "securedListPrescriptionsRequest"
})
@XmlRootElement(name = "ListPrescriptionsRequest")
public class ListPrescriptionsRequest
        extends RequestType
{

    @XmlElement(name = "SecuredListPrescriptionsRequest", required = true)
    protected SecuredContentType securedListPrescriptionsRequest;

    /**
     * Gets the value of the securedListPrescriptionsRequest property.
     *
     * @return
     *     possible object is
     *     {@link SecuredContentType }
     *
     */
    public SecuredContentType getSecuredListPrescriptionsRequest() {
        return securedListPrescriptionsRequest;
    }

    /**
     * Sets the value of the securedListPrescriptionsRequest property.
     *
     * @param value
     *     allowed object is
     *     {@link SecuredContentType }
     *
     */
    public void setSecuredListPrescriptionsRequest(SecuredContentType value) {
        this.securedListPrescriptionsRequest = value;
    }

}
