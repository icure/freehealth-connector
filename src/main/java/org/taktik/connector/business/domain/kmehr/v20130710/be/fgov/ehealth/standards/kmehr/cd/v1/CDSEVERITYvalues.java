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
 * <p>Classe Java pour CD-SEVERITYvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-SEVERITYvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="abnormal"/>
 *     &lt;enumeration value="high"/>
 *     &lt;enumeration value="low"/>
 *     &lt;enumeration value="normal"/>
 *     &lt;enumeration value="resistent"/>
 *     &lt;enumeration value="susceptible"/>
 *     &lt;enumeration value="susceptibleintermediate"/>
 *     &lt;enumeration value="veryabnormal"/>
 *     &lt;enumeration value="veryhigh"/>
 *     &lt;enumeration value="verylow"/>
 *     &lt;enumeration value="extremelyhigh"/>
 *     &lt;enumeration value="extremelylow"/>
 *     &lt;enumeration value="verysusceptible"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-SEVERITYvalues")
@XmlEnum
public enum CDSEVERITYvalues {

    @XmlEnumValue("abnormal")
    ABNORMAL("abnormal"),
    @XmlEnumValue("high")
    HIGH("high"),
    @XmlEnumValue("low")
    LOW("low"),
    @XmlEnumValue("normal")
    NORMAL("normal"),
    @XmlEnumValue("resistent")
    RESISTENT("resistent"),
    @XmlEnumValue("susceptible")
    SUSCEPTIBLE("susceptible"),
    @XmlEnumValue("susceptibleintermediate")
    SUSCEPTIBLEINTERMEDIATE("susceptibleintermediate"),
    @XmlEnumValue("veryabnormal")
    VERYABNORMAL("veryabnormal"),
    @XmlEnumValue("veryhigh")
    VERYHIGH("veryhigh"),
    @XmlEnumValue("verylow")
    VERYLOW("verylow"),
    @XmlEnumValue("extremelyhigh")
    EXTREMELYHIGH("extremelyhigh"),
    @XmlEnumValue("extremelylow")
    EXTREMELYLOW("extremelylow"),
    @XmlEnumValue("verysusceptible")
    VERYSUSCEPTIBLE("verysusceptible");
    private final String value;

    CDSEVERITYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSEVERITYvalues fromValue(String v) {
        for (CDSEVERITYvalues c: CDSEVERITYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
