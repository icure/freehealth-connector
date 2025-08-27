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
 * <p>Classe Java pour CD-ORTHO-APPROACHvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ORTHO-APPROACHvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="lateral"/>
 *     &lt;enumeration value="posterior"/>
 *     &lt;enumeration value="anterior"/>
 *     &lt;enumeration value="bytrochanterotomy"/>
 *     &lt;enumeration value="withfemoralosteotomy"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="subvastus"/>
 *     &lt;enumeration value="midvastus"/>
 *     &lt;enumeration value="parapatellarlateral"/>
 *     &lt;enumeration value="parapatellarmedial"/>
 *     &lt;enumeration value="tuberositasosteotomie"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ORTHO-APPROACHvalues")
@XmlEnum
public enum CDORTHOAPPROACHvalues {

    @XmlEnumValue("lateral")
    LATERAL("lateral"),
    @XmlEnumValue("posterior")
    POSTERIOR("posterior"),
    @XmlEnumValue("anterior")
    ANTERIOR("anterior"),
    @XmlEnumValue("bytrochanterotomy")
    BYTROCHANTEROTOMY("bytrochanterotomy"),
    @XmlEnumValue("withfemoralosteotomy")
    WITHFEMORALOSTEOTOMY("withfemoralosteotomy"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("subvastus")
    SUBVASTUS("subvastus"),
    @XmlEnumValue("midvastus")
    MIDVASTUS("midvastus"),
    @XmlEnumValue("parapatellarlateral")
    PARAPATELLARLATERAL("parapatellarlateral"),
    @XmlEnumValue("parapatellarmedial")
    PARAPATELLARMEDIAL("parapatellarmedial"),
    @XmlEnumValue("tuberositasosteotomie")
    TUBEROSITASOSTEOTOMIE("tuberositasosteotomie");
    private final String value;

    CDORTHOAPPROACHvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDORTHOAPPROACHvalues fromValue(String v) {
        for (CDORTHOAPPROACHvalues c: CDORTHOAPPROACHvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
