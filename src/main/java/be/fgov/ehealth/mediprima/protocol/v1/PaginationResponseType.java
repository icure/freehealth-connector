
package be.fgov.ehealth.mediprima.protocol.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * eHealth SOA Response Type for Services that require a Pagination as input.
 *
 * <p>Classe Java pour PaginationResponseType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="PaginationResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}ResponseType">
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:commons:core:v2}PaginationAttributeGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaginationResponseType", namespace = "urn:be:fgov:ehealth:commons:protocol:v2")
public class PaginationResponseType
    extends ResponseType
{

    @XmlAttribute(name = "Offset", required = true)
    protected int offset;
    @XmlAttribute(name = "MaxElements", required = true)
    protected int maxElements;

    /**
     * Obtient la valeur de la propriété offset.
     *
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Définit la valeur de la propriété offset.
     *
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Obtient la valeur de la propriété maxElements.
     *
     */
    public int getMaxElements() {
        return maxElements;
    }

    /**
     * Définit la valeur de la propriété maxElements.
     *
     */
    public void setMaxElements(int value) {
        this.maxElements = value;
    }

}
