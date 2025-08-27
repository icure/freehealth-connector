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
 * <p>Classe Java pour CD-IMPLANTATION-TYPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-IMPLANTATION-TYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unimedial"/>
 *     &lt;enumeration value="unilateral"/>
 *     &lt;enumeration value="bicompartimental"/>
 *     &lt;enumeration value="femoropatellar"/>
 *     &lt;enumeration value="totalcr"/>
 *     &lt;enumeration value="totalps"/>
 *     &lt;enumeration value="totalcck"/>
 *     &lt;enumeration value="hinge"/>
 *     &lt;enumeration value="totaluc"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-IMPLANTATION-TYPEvalues")
@XmlEnum
public enum CDIMPLANTATIONTYPEvalues {

    @XmlEnumValue("unimedial")
    UNIMEDIAL("unimedial"),
    @XmlEnumValue("unilateral")
    UNILATERAL("unilateral"),
    @XmlEnumValue("bicompartimental")
    BICOMPARTIMENTAL("bicompartimental"),
    @XmlEnumValue("femoropatellar")
    FEMOROPATELLAR("femoropatellar"),
    @XmlEnumValue("totalcr")
    TOTALCR("totalcr"),
    @XmlEnumValue("totalps")
    TOTALPS("totalps"),
    @XmlEnumValue("totalcck")
    TOTALCCK("totalcck"),
    @XmlEnumValue("hinge")
    HINGE("hinge"),
    @XmlEnumValue("totaluc")
    TOTALUC("totaluc"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    CDIMPLANTATIONTYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDIMPLANTATIONTYPEvalues fromValue(String v) {
        for (CDIMPLANTATIONTYPEvalues c: CDIMPLANTATIONTYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
