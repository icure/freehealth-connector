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
 * <p>Classe Java pour CD-SUBSTANCEschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-SUBSTANCEschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-INNCLUSTER"/>
 *     &lt;enumeration value="CD-ATC"/>
 *     &lt;enumeration value="CD-EAN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-SUBSTANCEschemes")
@XmlEnum
public enum CDSUBSTANCEschemes {

    @XmlEnumValue("CD-INNCLUSTER")
    CD_INNCLUSTER("CD-INNCLUSTER"),
    @XmlEnumValue("CD-ATC")
    CD_ATC("CD-ATC"),
    @XmlEnumValue("CD-EAN")
    CD_EAN("CD-EAN");
    private final String value;

    CDSUBSTANCEschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSUBSTANCEschemes fromValue(String v) {
        for (CDSUBSTANCEschemes c: CDSUBSTANCEschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
