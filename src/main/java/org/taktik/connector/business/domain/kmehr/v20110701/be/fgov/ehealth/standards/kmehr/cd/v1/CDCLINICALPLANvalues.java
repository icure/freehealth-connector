//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:47:59 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-CLINICALPLANvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CLINICALPLANvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="breastcancerprevention"/>
 *     &lt;enumeration value="cervixutericancer"/>
 *     &lt;enumeration value="colrectalcancerprevention"/>
 *     &lt;enumeration value="primaryprevention"/>
 *     &lt;enumeration value="prostatecancerprevention"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-CLINICALPLANvalues")
@XmlEnum
public enum CDCLINICALPLANvalues {

    @XmlEnumValue("breastcancerprevention")
    BREASTCANCERPREVENTION("breastcancerprevention"),
    @XmlEnumValue("cervixutericancer")
    CERVIXUTERICANCER("cervixutericancer"),
    @XmlEnumValue("colrectalcancerprevention")
    COLRECTALCANCERPREVENTION("colrectalcancerprevention"),
    @XmlEnumValue("primaryprevention")
    PRIMARYPREVENTION("primaryprevention"),
    @XmlEnumValue("prostatecancerprevention")
    PROSTATECANCERPREVENTION("prostatecancerprevention");
    private final String value;

    CDCLINICALPLANvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCLINICALPLANvalues fromValue(String v) {
        for (CDCLINICALPLANvalues c: CDCLINICALPLANvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
