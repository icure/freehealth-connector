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
 * <p>Classe Java pour CD-ORTHO-TECHREVISIONvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ORTHO-TECHREVISIONvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="femoralheadneck"/>
 *     &lt;enumeration value="femoralcomplete"/>
 *     &lt;enumeration value="acetabularinsert"/>
 *     &lt;enumeration value="acetabularcomplete"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ORTHO-TECHREVISIONvalues")
@XmlEnum
public enum CDORTHOTECHREVISIONvalues {

    @XmlEnumValue("femoralheadneck")
    FEMORALHEADNECK("femoralheadneck"),
    @XmlEnumValue("femoralcomplete")
    FEMORALCOMPLETE("femoralcomplete"),
    @XmlEnumValue("acetabularinsert")
    ACETABULARINSERT("acetabularinsert"),
    @XmlEnumValue("acetabularcomplete")
    ACETABULARCOMPLETE("acetabularcomplete");
    private final String value;

    CDORTHOTECHREVISIONvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDORTHOTECHREVISIONvalues fromValue(String v) {
        for (CDORTHOTECHREVISIONvalues c: CDORTHOTECHREVISIONvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
