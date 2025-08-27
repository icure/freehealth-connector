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
 * <p>Classe Java pour CD-HEADINGvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-HEADINGvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="assessment"/>
 *     &lt;enumeration value="clinical"/>
 *     &lt;enumeration value="clinicalplan"/>
 *     &lt;enumeration value="subjective"/>
 *     &lt;enumeration value="technical"/>
 *     &lt;enumeration value="technicalplan"/>
 *     &lt;enumeration value="treatment"/>
 *     &lt;enumeration value="userdefined"/>
 *     &lt;enumeration value="history"/>
 *     &lt;enumeration value="familyhistory"/>
 *     &lt;enumeration value="prescription"/>
 *     &lt;enumeration value="medication"/>
 *     &lt;enumeration value="anamnesis"/>
 *     &lt;enumeration value="examination"/>
 *     &lt;enumeration value="plan"/>
 *     &lt;enumeration value="outcomeevaluation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-HEADINGvalues")
@XmlEnum
public enum CDHEADINGvalues {

    @XmlEnumValue("assessment")
    ASSESSMENT("assessment"),
    @XmlEnumValue("clinical")
    CLINICAL("clinical"),
    @XmlEnumValue("clinicalplan")
    CLINICALPLAN("clinicalplan"),
    @XmlEnumValue("subjective")
    SUBJECTIVE("subjective"),
    @XmlEnumValue("technical")
    TECHNICAL("technical"),
    @XmlEnumValue("technicalplan")
    TECHNICALPLAN("technicalplan"),
    @XmlEnumValue("treatment")
    TREATMENT("treatment"),
    @XmlEnumValue("userdefined")
    USERDEFINED("userdefined"),
    @XmlEnumValue("history")
    HISTORY("history"),
    @XmlEnumValue("familyhistory")
    FAMILYHISTORY("familyhistory"),
    @XmlEnumValue("prescription")
    PRESCRIPTION("prescription"),
    @XmlEnumValue("medication")
    MEDICATION("medication"),
    @XmlEnumValue("anamnesis")
    ANAMNESIS("anamnesis"),
    @XmlEnumValue("examination")
    EXAMINATION("examination"),
    @XmlEnumValue("plan")
    PLAN("plan"),
    @XmlEnumValue("outcomeevaluation")
    OUTCOMEEVALUATION("outcomeevaluation");
    private final String value;

    CDHEADINGvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDHEADINGvalues fromValue(String v) {
        for (CDHEADINGvalues c: CDHEADINGvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
