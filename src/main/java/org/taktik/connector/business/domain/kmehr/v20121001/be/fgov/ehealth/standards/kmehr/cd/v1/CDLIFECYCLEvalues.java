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
 * <p>Classe Java pour CD-LIFECYCLEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-LIFECYCLEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="aborted"/>
 *     &lt;enumeration value="active"/>
 *     &lt;enumeration value="added"/>
 *     &lt;enumeration value="administrated"/>
 *     &lt;enumeration value="cancelled"/>
 *     &lt;enumeration value="completed"/>
 *     &lt;enumeration value="corrected"/>
 *     &lt;enumeration value="delivered"/>
 *     &lt;enumeration value="substituted"/>
 *     &lt;enumeration value="inactive"/>
 *     &lt;enumeration value="planned"/>
 *     &lt;enumeration value="prescribed"/>
 *     &lt;enumeration value="reported"/>
 *     &lt;enumeration value="refused"/>
 *     &lt;enumeration value="switched"/>
 *     &lt;enumeration value="suspended"/>
 *     &lt;enumeration value="stopped"/>
 *     &lt;enumeration value="excluded"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-LIFECYCLEvalues")
@XmlEnum
public enum CDLIFECYCLEvalues {

    @XmlEnumValue("aborted")
    ABORTED("aborted"),
    @XmlEnumValue("active")
    ACTIVE("active"),
    @XmlEnumValue("added")
    ADDED("added"),
    @XmlEnumValue("administrated")
    ADMINISTRATED("administrated"),
    @XmlEnumValue("cancelled")
    CANCELLED("cancelled"),
    @XmlEnumValue("completed")
    COMPLETED("completed"),
    @XmlEnumValue("corrected")
    CORRECTED("corrected"),
    @XmlEnumValue("delivered")
    DELIVERED("delivered"),
    @XmlEnumValue("substituted")
    SUBSTITUTED("substituted"),
    @XmlEnumValue("inactive")
    INACTIVE("inactive"),
    @XmlEnumValue("planned")
    PLANNED("planned"),
    @XmlEnumValue("prescribed")
    PRESCRIBED("prescribed"),
    @XmlEnumValue("reported")
    REPORTED("reported"),
    @XmlEnumValue("refused")
    REFUSED("refused"),
    @XmlEnumValue("switched")
    SWITCHED("switched"),
    @XmlEnumValue("suspended")
    SUSPENDED("suspended"),
    @XmlEnumValue("stopped")
    STOPPED("stopped"),
    @XmlEnumValue("excluded")
    EXCLUDED("excluded");
    private final String value;

    CDLIFECYCLEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDLIFECYCLEvalues fromValue(String v) {
        for (CDLIFECYCLEvalues c: CDLIFECYCLEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
