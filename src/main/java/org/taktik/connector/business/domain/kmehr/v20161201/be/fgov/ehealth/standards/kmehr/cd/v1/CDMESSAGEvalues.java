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
 * <p>Classe Java pour CD-MESSAGEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-MESSAGEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="gpsoftwaremigration"/>
 *     &lt;enumeration value="gppatientmigration"/>
 *     &lt;enumeration value="ptsoftwaremigration"/>
 *     &lt;enumeration value="ptpatientmigration"/>
 *     &lt;enumeration value="nursingsoftwaremigration"/>
 *     &lt;enumeration value="nursingpatientmigration"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-MESSAGEvalues")
@XmlEnum
public enum CDMESSAGEvalues {

    @XmlEnumValue("gpsoftwaremigration")
    GPSOFTWAREMIGRATION("gpsoftwaremigration"),
    @XmlEnumValue("gppatientmigration")
    GPPATIENTMIGRATION("gppatientmigration"),
    @XmlEnumValue("ptsoftwaremigration")
    PTSOFTWAREMIGRATION("ptsoftwaremigration"),
    @XmlEnumValue("ptpatientmigration")
    PTPATIENTMIGRATION("ptpatientmigration"),
    @XmlEnumValue("nursingsoftwaremigration")
    NURSINGSOFTWAREMIGRATION("nursingsoftwaremigration"),
    @XmlEnumValue("nursingpatientmigration")
    NURSINGPATIENTMIGRATION("nursingpatientmigration");
    private final String value;

    CDMESSAGEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDMESSAGEvalues fromValue(String v) {
        for (CDMESSAGEvalues c: CDMESSAGEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
