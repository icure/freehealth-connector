//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-STENT-DEVICEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-STENT-DEVICEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="bms"/>
 *     &lt;enumeration value="des"/>
 *     &lt;enumeration value="bvs"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-STENT-DEVICEvalues")
@XmlEnum
public enum CDSTENTDEVICEvalues {

    @XmlEnumValue("bms")
    BMS("bms"),
    @XmlEnumValue("des")
    DES("des"),
    @XmlEnumValue("bvs")
    BVS("bvs"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    CDSTENTDEVICEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSTENTDEVICEvalues fromValue(String v) {
        for (CDSTENTDEVICEvalues c: CDSTENTDEVICEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
