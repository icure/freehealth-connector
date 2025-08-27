//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:09 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-BVT-STATUSvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-BVT-STATUSvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="saved"/>
 *     &lt;enumeration value="submitted"/>
 *     &lt;enumeration value="rejected"/>
 *     &lt;enumeration value="inactive"/>
 *     &lt;enumeration value="published"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-BVT-STATUSvalues")
@XmlEnum
public enum CDBVTSTATUSvalues {

    @XmlEnumValue("saved")
    SAVED("saved"),
    @XmlEnumValue("submitted")
    SUBMITTED("submitted"),
    @XmlEnumValue("rejected")
    REJECTED("rejected"),
    @XmlEnumValue("inactive")
    INACTIVE("inactive"),
    @XmlEnumValue("published")
    PUBLISHED("published");
    private final String value;

    CDBVTSTATUSvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDBVTSTATUSvalues fromValue(String v) {
        for (CDBVTSTATUSvalues c: CDBVTSTATUSvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
