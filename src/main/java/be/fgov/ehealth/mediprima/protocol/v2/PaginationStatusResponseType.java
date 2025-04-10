
package be.fgov.ehealth.mediprima.protocol.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * eHealth SOA Response Type for Services that require a Pagination as input and a Status as output.
 * 
 * <p>Classe Java pour PaginationStatusResponseType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PaginationStatusResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}StatusResponseType">
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:commons:core:v2}PaginationAttributeGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaginationStatusResponseType", namespace = "urn:be:fgov:ehealth:commons:protocol:v2")
public class PaginationStatusResponseType
    extends StatusResponseType
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
