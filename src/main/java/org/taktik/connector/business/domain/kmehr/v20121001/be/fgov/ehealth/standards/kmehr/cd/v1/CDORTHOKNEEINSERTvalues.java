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
 * <p>Classe Java pour CD-ORTHO-KNEE-INSERTvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ORTHO-KNEE-INSERTvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="fixed"/>
 *     &lt;enumeration value="mobile"/>
 *     &lt;enumeration value="none"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ORTHO-KNEE-INSERTvalues")
@XmlEnum
public enum CDORTHOKNEEINSERTvalues {

    @XmlEnumValue("fixed")
    FIXED("fixed"),
    @XmlEnumValue("mobile")
    MOBILE("mobile"),
    @XmlEnumValue("none")
    NONE("none");
    private final String value;

    CDORTHOKNEEINSERTvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDORTHOKNEEINSERTvalues fromValue(String v) {
        for (CDORTHOKNEEINSERTvalues c: CDORTHOKNEEINSERTvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
