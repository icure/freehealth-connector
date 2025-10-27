
package be.fgov.ehealth.mediprimaUma.protocol;

import be.fgov.ehealth.mediprimaUma.core.AuthorType;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * eHealth SOA Request Type for Services that require an Author as input.
 *
 * <p>Classe Java pour AuthorRequestType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="AuthorRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}RequestType">
 *       &lt;sequence>
 *         &lt;element name="Author" type="{urn:be:fgov:ehealth:commons:core:v2}AuthorType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorRequestType", propOrder = {
    "author"
})
@XmlSeeAlso({
    SendUrgentMedicalAidAttestationRequestType.class,
    DeleteUrgentMedicalAidAttestationRequestType.class,
    AuthorPaginationRequestType.class
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class AuthorRequestType
    extends RequestType
{

    @XmlElement(name = "Author", namespace = "urn:be:fgov:ehealth:commons:protocol:v2", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AuthorType author;

    /**
     * Obtient la valeur de la propriété author.
     *
     * @return
     *     possible object is
     *     {@link AuthorType }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public AuthorType getAuthor() {
        return author;
    }

    /**
     * Définit la valeur de la propriété author.
     *
     * @param value
     *     allowed object is
     *     {@link AuthorType }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:59+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAuthor(AuthorType value) {
        this.author = value;
    }

}
