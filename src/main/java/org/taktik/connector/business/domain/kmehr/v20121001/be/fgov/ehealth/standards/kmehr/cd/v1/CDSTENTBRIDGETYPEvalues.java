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
 * <p>Classe Java pour CD-STENT-BRIDGETYPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-STENT-BRIDGETYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="saphena1"/>
 *     &lt;enumeration value="saphena2"/>
 *     &lt;enumeration value="saphena3"/>
 *     &lt;enumeration value="saphena4"/>
 *     &lt;enumeration value="saphena5"/>
 *     &lt;enumeration value="lima"/>
 *     &lt;enumeration value="rima"/>
 *     &lt;enumeration value="gepa"/>
 *     &lt;enumeration value="freeima"/>
 *     &lt;enumeration value="radialis"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-STENT-BRIDGETYPEvalues")
@XmlEnum
public enum CDSTENTBRIDGETYPEvalues {

    @XmlEnumValue("saphena1")
    SAPHENA_1("saphena1"),
    @XmlEnumValue("saphena2")
    SAPHENA_2("saphena2"),
    @XmlEnumValue("saphena3")
    SAPHENA_3("saphena3"),
    @XmlEnumValue("saphena4")
    SAPHENA_4("saphena4"),
    @XmlEnumValue("saphena5")
    SAPHENA_5("saphena5"),
    @XmlEnumValue("lima")
    LIMA("lima"),
    @XmlEnumValue("rima")
    RIMA("rima"),
    @XmlEnumValue("gepa")
    GEPA("gepa"),
    @XmlEnumValue("freeima")
    FREEIMA("freeima"),
    @XmlEnumValue("radialis")
    RADIALIS("radialis");
    private final String value;

    CDSTENTBRIDGETYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSTENTBRIDGETYPEvalues fromValue(String v) {
        for (CDSTENTBRIDGETYPEvalues c: CDSTENTBRIDGETYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
