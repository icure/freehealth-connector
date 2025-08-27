
package be.fgov.ehealth.mycarenet.commons.core.v4;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Information about the request.
 *
 * <p>Classe Java pour RequestType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IsTest" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestType", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4", propOrder = {
    "isTest"
})
public class RequestType {

    @XmlElement(name = "IsTest", namespace = "urn:be:fgov:ehealth:mycarenet:commons:core:v4")
    protected boolean isTest;

    /**
     * Obtient la valeur de la propriété isTest.
     *
     */
    public boolean isIsTest() {
        return isTest;
    }

    /**
     * Définit la valeur de la propriété isTest.
     *
     */
    public void setIsTest(boolean value) {
        this.isTest = value;
    }

}
