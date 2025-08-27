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
 * <p>Classe Java pour CD-EBIRTH-DELIVERYWAYvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-EBIRTH-DELIVERYWAYvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="spontaneous"/>
 *     &lt;enumeration value="vacuum-extraction"/>
 *     &lt;enumeration value="forceps"/>
 *     &lt;enumeration value="primary-caesarean"/>
 *     &lt;enumeration value="secondary-caesarean"/>
 *     &lt;enumeration value="vaginal breech"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-EBIRTH-DELIVERYWAYvalues")
@XmlEnum
public enum CDEBIRTHDELIVERYWAYvalues {

    @XmlEnumValue("spontaneous")
    SPONTANEOUS("spontaneous"),
    @XmlEnumValue("vacuum-extraction")
    VACUUM_EXTRACTION("vacuum-extraction"),
    @XmlEnumValue("forceps")
    FORCEPS("forceps"),
    @XmlEnumValue("primary-caesarean")
    PRIMARY_CAESAREAN("primary-caesarean"),
    @XmlEnumValue("secondary-caesarean")
    SECONDARY_CAESAREAN("secondary-caesarean"),
    @XmlEnumValue("vaginal breech")
    VAGINAL_BREECH("vaginal breech");
    private final String value;

    CDEBIRTHDELIVERYWAYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDEBIRTHDELIVERYWAYvalues fromValue(String v) {
        for (CDEBIRTHDELIVERYWAYvalues c: CDEBIRTHDELIVERYWAYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
