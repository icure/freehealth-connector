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
 * <p>Classe Java pour CD-REFSCOPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-REFSCOPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="age"/>
 *     &lt;enumeration value="sex"/>
 *     &lt;enumeration value="gestationnal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-REFSCOPEvalues")
@XmlEnum
public enum CDREFSCOPEvalues {

    @XmlEnumValue("age")
    AGE("age"),
    @XmlEnumValue("sex")
    SEX("sex"),
    @XmlEnumValue("gestationnal")
    GESTATIONNAL("gestationnal");
    private final String value;

    CDREFSCOPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDREFSCOPEvalues fromValue(String v) {
        for (CDREFSCOPEvalues c: CDREFSCOPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
