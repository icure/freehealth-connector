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
 * <p>Classe Java pour CD-CERTAINTYvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CERTAINTYvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="excluded"/>
 *     &lt;enumeration value="probable"/>
 *     &lt;enumeration value="proven"/>
 *     &lt;enumeration value="unprobable"/>
 *     &lt;enumeration value="undefined"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-CERTAINTYvalues")
@XmlEnum
public enum CDCERTAINTYvalues {

    @XmlEnumValue("excluded")
    EXCLUDED("excluded"),
    @XmlEnumValue("probable")
    PROBABLE("probable"),
    @XmlEnumValue("proven")
    PROVEN("proven"),
    @XmlEnumValue("unprobable")
    UNPROBABLE("unprobable"),
    @XmlEnumValue("undefined")
    UNDEFINED("undefined");
    private final String value;

    CDCERTAINTYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCERTAINTYvalues fromValue(String v) {
        for (CDCERTAINTYvalues c: CDCERTAINTYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
