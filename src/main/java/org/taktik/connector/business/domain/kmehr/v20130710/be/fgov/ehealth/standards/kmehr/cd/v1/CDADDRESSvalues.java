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
 * <p>Classe Java pour CD-ADDRESSvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ADDRESSvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="home"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="vacation"/>
 *     &lt;enumeration value="work"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ADDRESSvalues")
@XmlEnum
public enum CDADDRESSvalues {

    @XmlEnumValue("home")
    HOME("home"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("vacation")
    VACATION("vacation"),
    @XmlEnumValue("work")
    WORK("work");
    private final String value;

    CDADDRESSvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDADDRESSvalues fromValue(String v) {
        for (CDADDRESSvalues c: CDADDRESSvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
