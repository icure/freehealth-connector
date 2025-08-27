
package be.fgov.ehealth.mediprima.protocol.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour InformationType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="InformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FieldName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FieldValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformationType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
    "fieldName",
    "fieldValue"
})
public class InformationType {

    @XmlElement(name = "FieldName", required = true)
    protected String fieldName;
    @XmlElement(name = "FieldValue")
    protected String fieldValue;

    /**
     * Obtient la valeur de la propriété fieldName.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Définit la valeur de la propriété fieldName.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Obtient la valeur de la propriété fieldValue.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * Définit la valeur de la propriété fieldValue.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFieldValue(String value) {
        this.fieldValue = value;
    }

}
