//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:06 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-QUANTITYPREFIXvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-QUANTITYPREFIXvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ana"/>
 *     &lt;enumeration value="anaad"/>
 *     &lt;enumeration value="ad"/>
 *     &lt;enumeration value="qs"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-QUANTITYPREFIXvalues")
@XmlEnum
public enum CDQUANTITYPREFIXvalues {

    @XmlEnumValue("ana")
    ANA("ana"),
    @XmlEnumValue("anaad")
    ANAAD("anaad"),
    @XmlEnumValue("ad")
    AD("ad"),
    @XmlEnumValue("qs")
    QS("qs");
    private final String value;

    CDQUANTITYPREFIXvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDQUANTITYPREFIXvalues fromValue(String v) {
        for (CDQUANTITYPREFIXvalues c: CDQUANTITYPREFIXvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
