
package be.fgov.ehealth.mediprima.core.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Identifies the type of Actor
 *
 * <p>Classe Java pour ActorType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="ActorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:be:fgov:ehealth:commons:core:v2}Id" maxOccurs="unbounded"/>
 *         &lt;sequence>
 *           &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActorType", namespace = "urn:be:fgov:ehealth:commons:core:v2", propOrder = {
    "id",
    "firstName",
    "name"
})
public class ActorType {

    @XmlElement(name = "Id", namespace = "urn:be:fgov:ehealth:commons:core:v2", required = true)
    protected List<IdType> id;
    @XmlElement(name = "FirstName", namespace = "urn:be:fgov:ehealth:commons:core:v2")
    protected List<String> firstName;
    @XmlElement(name = "Name", namespace = "urn:be:fgov:ehealth:commons:core:v2", required = true)
    protected String name;

    /**
     * Gets the value of the id property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the id property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdType }
     *
     *
     */
    public List<IdType> getId() {
        if (id == null) {
            id = new ArrayList<IdType>();
        }
        return this.id;
    }

    /**
     * Gets the value of the firstName property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the firstName property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFirstName().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getFirstName() {
        if (firstName == null) {
            firstName = new ArrayList<String>();
        }
        return this.firstName;
    }

    /**
     * Obtient la valeur de la propriété name.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

}
