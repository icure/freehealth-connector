
package be.fgov.ehealth.mediprima.core.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ConsultCarmedDataType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="ConsultCarmedDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BySsin" type="{urn:be:fgov:ehealth:mediprima:core:v1}BySsinType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultCarmedDataType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
    "bySsin"
})
public class ConsultCarmedDataType {

    @XmlElement(name = "BySsin", required = true)
    protected BySsinType bySsin;

    /**
     * Obtient la valeur de la propriété bySsin.
     *
     * @return
     *     possible object is
     *     {@link BySsinType }
     *
     */
    public BySsinType getBySsin() {
        return bySsin;
    }

    /**
     * Définit la valeur de la propriété bySsin.
     *
     * @param value
     *     allowed object is
     *     {@link BySsinType }
     *
     */
    public void setBySsin(BySsinType value) {
        this.bySsin = value;
    }

}
