
package be.fgov.ehealth.mediprima.core.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * eHealth SOA Request Type for Services that require an author and pagination as input.
 * 
 * <p>Classe Java pour AuthorPaginationRequestType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AuthorPaginationRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:commons:protocol:v2}AuthorRequestType">
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:commons:core:v2}PaginationAttributeGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorPaginationRequestType", namespace = "urn:be:fgov:ehealth:commons:protocol:v2")
public class AuthorPaginationRequestType
    extends AuthorRequestType
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
