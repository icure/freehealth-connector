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
 * <p>Classe Java pour CD-MAA-RESPONSETYPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-MAA-RESPONSETYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="agreement"/>
 *     &lt;enumeration value="refusal"/>
 *     &lt;enumeration value="intreatment"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-MAA-RESPONSETYPEvalues")
@XmlEnum
public enum CDMAARESPONSETYPEvalues {

    @XmlEnumValue("agreement")
    AGREEMENT("agreement"),
    @XmlEnumValue("refusal")
    REFUSAL("refusal"),
    @XmlEnumValue("intreatment")
    INTREATMENT("intreatment");
    private final String value;

    CDMAARESPONSETYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDMAARESPONSETYPEvalues fromValue(String v) {
        for (CDMAARESPONSETYPEvalues c: CDMAARESPONSETYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
