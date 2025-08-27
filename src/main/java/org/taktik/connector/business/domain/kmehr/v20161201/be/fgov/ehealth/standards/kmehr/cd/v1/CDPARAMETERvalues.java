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
 * <p>Classe Java pour CD-PARAMETERvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-PARAMETERvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="weight"/>
 *     &lt;enumeration value="height"/>
 *     &lt;enumeration value="bmi"/>
 *     &lt;enumeration value="sbp"/>
 *     &lt;enumeration value="dbp"/>
 *     &lt;enumeration value="pulsecharacter"/>
 *     &lt;enumeration value="heartrate"/>
 *     &lt;enumeration value="peakflow"/>
 *     &lt;enumeration value="gpa"/>
 *     &lt;enumeration value="headcircumference"/>
 *     &lt;enumeration value="hipcircumference"/>
 *     &lt;enumeration value="apgar"/>
 *     &lt;enumeration value="katz"/>
 *     &lt;enumeration value="belrai"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-PARAMETERvalues")
@XmlEnum
public enum CDPARAMETERvalues {

    @XmlEnumValue("weight")
    WEIGHT("weight"),
    @XmlEnumValue("height")
    HEIGHT("height"),
    @XmlEnumValue("bmi")
    BMI("bmi"),
    @XmlEnumValue("sbp")
    SBP("sbp"),
    @XmlEnumValue("dbp")
    DBP("dbp"),
    @XmlEnumValue("pulsecharacter")
    PULSECHARACTER("pulsecharacter"),
    @XmlEnumValue("heartrate")
    HEARTRATE("heartrate"),
    @XmlEnumValue("peakflow")
    PEAKFLOW("peakflow"),
    @XmlEnumValue("gpa")
    GPA("gpa"),
    @XmlEnumValue("headcircumference")
    HEADCIRCUMFERENCE("headcircumference"),
    @XmlEnumValue("hipcircumference")
    HIPCIRCUMFERENCE("hipcircumference"),
    @XmlEnumValue("apgar")
    APGAR("apgar"),
    @XmlEnumValue("katz")
    KATZ("katz"),
    @XmlEnumValue("belrai")
    BELRAI("belrai");
    private final String value;

    CDPARAMETERvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDPARAMETERvalues fromValue(String v) {
        for (CDPARAMETERvalues c: CDPARAMETERvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
