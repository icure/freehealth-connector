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
 * <p>Classe Java pour CD-TUCO-PATHOLOGYTYPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TUCO-PATHOLOGYTYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="diabetedietarycontrol"/>
 *     &lt;enumeration value="diabeteoralmedication"/>
 *     &lt;enumeration value="diabeteinsulin"/>
 *     &lt;enumeration value="diabetenewlydiagnosed"/>
 *     &lt;enumeration value="diabete"/>
 *     &lt;enumeration value="renalfailurelessthan30ml"/>
 *     &lt;enumeration value="instentrestenosis"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TUCO-PATHOLOGYTYPEvalues")
@XmlEnum
public enum CDTUCOPATHOLOGYTYPEvalues {

    @XmlEnumValue("diabetedietarycontrol")
    DIABETEDIETARYCONTROL("diabetedietarycontrol"),
    @XmlEnumValue("diabeteoralmedication")
    DIABETEORALMEDICATION("diabeteoralmedication"),
    @XmlEnumValue("diabeteinsulin")
    DIABETEINSULIN("diabeteinsulin"),
    @XmlEnumValue("diabetenewlydiagnosed")
    DIABETENEWLYDIAGNOSED("diabetenewlydiagnosed"),
    @XmlEnumValue("diabete")
    DIABETE("diabete"),
    @XmlEnumValue("renalfailurelessthan30ml")
    RENALFAILURELESSTHAN_30_ML("renalfailurelessthan30ml"),
    @XmlEnumValue("instentrestenosis")
    INSTENTRESTENOSIS("instentrestenosis");
    private final String value;

    CDTUCOPATHOLOGYTYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTUCOPATHOLOGYTYPEvalues fromValue(String v) {
        for (CDTUCOPATHOLOGYTYPEvalues c: CDTUCOPATHOLOGYTYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
