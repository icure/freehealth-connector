//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:47:59 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-TELECOMschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TELECOMschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-ADDRESS"/>
 *     &lt;enumeration value="CD-TELECOM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TELECOMschemes")
@XmlEnum
public enum CDTELECOMschemes {

    @XmlEnumValue("CD-ADDRESS")
    CD_ADDRESS("CD-ADDRESS"),
    @XmlEnumValue("CD-TELECOM")
    CD_TELECOM("CD-TELECOM");
    private final String value;

    CDTELECOMschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTELECOMschemes fromValue(String v) {
        for (CDTELECOMschemes c: CDTELECOMschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
