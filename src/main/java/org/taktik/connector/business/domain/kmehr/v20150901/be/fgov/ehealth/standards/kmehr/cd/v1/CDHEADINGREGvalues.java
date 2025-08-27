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
 * <p>Classe Java pour CD-HEADING-REGvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-HEADING-REGvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="coronaryanatomy"/>
 *     &lt;enumeration value="chapter4"/>
 *     &lt;enumeration value="non-biologic"/>
 *     &lt;enumeration value="bmi"/>
 *     &lt;enumeration value="primarykneeprocedure"/>
 *     &lt;enumeration value="approachtechnic"/>
 *     &lt;enumeration value="instrumentation"/>
 *     &lt;enumeration value="orthopedicanatomy"/>
 *     &lt;enumeration value="interface"/>
 *     &lt;enumeration value="revisionplan"/>
 *     &lt;enumeration value="material"/>
 *     &lt;enumeration value="notified-material"/>
 *     &lt;enumeration value="not-notified-material"/>
 *     &lt;enumeration value="not-notified-ortho-device"/>
 *     &lt;enumeration value="criteria"/>
 *     &lt;enumeration value="comorbidities"/>
 *     &lt;enumeration value="comorbiditiesinformation"/>
 *     &lt;enumeration value="results"/>
 *     &lt;enumeration value="resynchronisationinfo"/>
 *     &lt;enumeration value="crtp"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-HEADING-REGvalues")
@XmlEnum
public enum CDHEADINGREGvalues {

    @XmlEnumValue("coronaryanatomy")
    CORONARYANATOMY("coronaryanatomy"),
    @XmlEnumValue("chapter4")
    CHAPTER_4("chapter4"),
    @XmlEnumValue("non-biologic")
    NON_BIOLOGIC("non-biologic"),
    @XmlEnumValue("bmi")
    BMI("bmi"),
    @XmlEnumValue("primarykneeprocedure")
    PRIMARYKNEEPROCEDURE("primarykneeprocedure"),
    @XmlEnumValue("approachtechnic")
    APPROACHTECHNIC("approachtechnic"),
    @XmlEnumValue("instrumentation")
    INSTRUMENTATION("instrumentation"),
    @XmlEnumValue("orthopedicanatomy")
    ORTHOPEDICANATOMY("orthopedicanatomy"),
    @XmlEnumValue("interface")
    INTERFACE("interface"),
    @XmlEnumValue("revisionplan")
    REVISIONPLAN("revisionplan"),
    @XmlEnumValue("material")
    MATERIAL("material"),
    @XmlEnumValue("notified-material")
    NOTIFIED_MATERIAL("notified-material"),
    @XmlEnumValue("not-notified-material")
    NOT_NOTIFIED_MATERIAL("not-notified-material"),
    @XmlEnumValue("not-notified-ortho-device")
    NOT_NOTIFIED_ORTHO_DEVICE("not-notified-ortho-device"),
    @XmlEnumValue("criteria")
    CRITERIA("criteria"),
    @XmlEnumValue("comorbidities")
    COMORBIDITIES("comorbidities"),
    @XmlEnumValue("comorbiditiesinformation")
    COMORBIDITIESINFORMATION("comorbiditiesinformation"),
    @XmlEnumValue("results")
    RESULTS("results"),
    @XmlEnumValue("resynchronisationinfo")
    RESYNCHRONISATIONINFO("resynchronisationinfo"),
    @XmlEnumValue("crtp")
    CRTP("crtp");
    private final String value;

    CDHEADINGREGvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDHEADINGREGvalues fromValue(String v) {
        for (CDHEADINGREGvalues c: CDHEADINGREGvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
