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
 * <p>Classe Java pour CD-TELECOMvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TELECOMvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="phone"/>
 *     &lt;enumeration value="mobile"/>
 *     &lt;enumeration value="fax"/>
 *     &lt;enumeration value="email"/>
 *     &lt;enumeration value="carenet"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TELECOMvalues")
@XmlEnum
public enum CDTELECOMvalues {

    @XmlEnumValue("phone")
    PHONE("phone"),
    @XmlEnumValue("mobile")
    MOBILE("mobile"),
    @XmlEnumValue("fax")
    FAX("fax"),
    @XmlEnumValue("email")
    EMAIL("email"),
    @XmlEnumValue("carenet")
    CARENET("carenet");
    private final String value;

    CDTELECOMvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTELECOMvalues fromValue(String v) {
        for (CDTELECOMvalues c: CDTELECOMvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
