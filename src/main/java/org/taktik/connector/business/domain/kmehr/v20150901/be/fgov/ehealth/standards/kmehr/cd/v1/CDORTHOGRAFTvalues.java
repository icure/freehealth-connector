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
 * <p>Classe Java pour CD-ORTHO-GRAFTvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ORTHO-GRAFTvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="allograft"/>
 *     &lt;enumeration value="homograft"/>
 *     &lt;enumeration value="autograft"/>
 *     &lt;enumeration value="alloandautograft"/>
 *     &lt;enumeration value="none"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ORTHO-GRAFTvalues")
@XmlEnum
public enum CDORTHOGRAFTvalues {

    @XmlEnumValue("allograft")
    ALLOGRAFT("allograft"),
    @XmlEnumValue("homograft")
    HOMOGRAFT("homograft"),
    @XmlEnumValue("autograft")
    AUTOGRAFT("autograft"),
    @XmlEnumValue("alloandautograft")
    ALLOANDAUTOGRAFT("alloandautograft"),
    @XmlEnumValue("none")
    NONE("none");
    private final String value;

    CDORTHOGRAFTvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDORTHOGRAFTvalues fromValue(String v) {
        for (CDORTHOGRAFTvalues c: CDORTHOGRAFTvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
