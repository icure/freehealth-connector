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
 * <p>Classe Java pour CD-HCPARTYschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-HCPARTYschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-HCPARTY"/>
 *     &lt;enumeration value="CD-APPLICATION"/>
 *     &lt;enumeration value="CD-ENCRYPTION-ACTOR"/>
 *     &lt;enumeration value="LOCAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-HCPARTYschemes")
@XmlEnum
public enum CDHCPARTYschemes {

    @XmlEnumValue("CD-HCPARTY")
    CD_HCPARTY("CD-HCPARTY"),
    @XmlEnumValue("CD-APPLICATION")
    CD_APPLICATION("CD-APPLICATION"),
    @XmlEnumValue("CD-ENCRYPTION-ACTOR")
    CD_ENCRYPTION_ACTOR("CD-ENCRYPTION-ACTOR"),
    LOCAL("LOCAL");
    private final String value;

    CDHCPARTYschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDHCPARTYschemes fromValue(String v) {
        for (CDHCPARTYschemes c: CDHCPARTYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
