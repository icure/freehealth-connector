//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.11.10 à 11:53:46 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-MEDICATIONschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-MEDICATIONschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-DRUG-CNK"/>
 *     &lt;enumeration value="CD-VACCINE"/>
 *     &lt;enumeration value="LOCAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-MEDICATIONschemes")
@XmlEnum
public enum CDMEDICATIONschemes {

    @XmlEnumValue("CD-DRUG-CNK")
    CD_DRUG_CNK("CD-DRUG-CNK"),
    @XmlEnumValue("CD-VACCINE")
    CD_VACCINE("CD-VACCINE"),
    LOCAL("LOCAL");
    private final String value;

    CDMEDICATIONschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDMEDICATIONschemes fromValue(String v) {
        for (CDMEDICATIONschemes c: CDMEDICATIONschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
