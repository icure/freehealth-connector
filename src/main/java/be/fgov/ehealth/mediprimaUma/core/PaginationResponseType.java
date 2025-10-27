
package be.fgov.ehealth.mediprimaUma.core;

import be.fgov.ehealth.mediprimaUma.protocol.ResponseType;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


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
@XmlType(name = "PaginationResponseType")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:44+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class PaginationResponseType
    extends ResponseType
{

    @XmlAttribute(name = "Offset", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:44+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected int offset;
    @XmlAttribute(name = "MaxElements", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:44+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected int maxElements;

    /**
     * Obtient la valeur de la propriété offset.
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:44+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public int getOffset() {
        return offset;
    }

    /**
     * Définit la valeur de la propriété offset.
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:44+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Obtient la valeur de la propriété maxElements.
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:44+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public int getMaxElements() {
        return maxElements;
    }

    /**
     * Définit la valeur de la propriété maxElements.
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2025-10-27T11:37:44+01:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setMaxElements(int value) {
        this.maxElements = value;
    }

}
