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
 * <p>Classe Java pour CD-EBIRTH-FOETALMONITORINGvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-EBIRTH-FOETALMONITORINGvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CTG"/>
 *     &lt;enumeration value="STAN"/>
 *     &lt;enumeration value="MBE"/>
 *     &lt;enumeration value="intermittent-auscultation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-EBIRTH-FOETALMONITORINGvalues")
@XmlEnum
public enum CDEBIRTHFOETALMONITORINGvalues {

    CTG("CTG"),
    STAN("STAN"),
    MBE("MBE"),
    @XmlEnumValue("intermittent-auscultation")
    INTERMITTENT_AUSCULTATION("intermittent-auscultation");
    private final String value;

    CDEBIRTHFOETALMONITORINGvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDEBIRTHFOETALMONITORINGvalues fromValue(String v) {
        for (CDEBIRTHFOETALMONITORINGvalues c: CDEBIRTHFOETALMONITORINGvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
