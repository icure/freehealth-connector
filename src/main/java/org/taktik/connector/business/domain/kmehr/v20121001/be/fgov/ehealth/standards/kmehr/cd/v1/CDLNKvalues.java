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
 * <p>Classe Java pour CD-LNKvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-LNKvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="isachildof"/>
 *     &lt;enumeration value="isaconsequenceof"/>
 *     &lt;enumeration value="isanewversionof"/>
 *     &lt;enumeration value="isareplyto"/>
 *     &lt;enumeration value="multimedia"/>
 *     &lt;enumeration value="thumbnail"/>
 *     &lt;enumeration value="isanappendixof"/>
 *     &lt;enumeration value="isaservicefor"/>
 *     &lt;enumeration value="isrealisationof"/>
 *     &lt;enumeration value="isapproachfor"/>
 *     &lt;enumeration value="isplannedfor"/>
 *     &lt;enumeration value="isattestationof"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-LNKvalues")
@XmlEnum
public enum CDLNKvalues {

    @XmlEnumValue("isachildof")
    ISACHILDOF("isachildof"),
    @XmlEnumValue("isaconsequenceof")
    ISACONSEQUENCEOF("isaconsequenceof"),
    @XmlEnumValue("isanewversionof")
    ISANEWVERSIONOF("isanewversionof"),
    @XmlEnumValue("isareplyto")
    ISAREPLYTO("isareplyto"),
    @XmlEnumValue("multimedia")
    MULTIMEDIA("multimedia"),
    @XmlEnumValue("thumbnail")
    THUMBNAIL("thumbnail"),
    @XmlEnumValue("isanappendixof")
    ISANAPPENDIXOF("isanappendixof"),
    @XmlEnumValue("isaservicefor")
    ISASERVICEFOR("isaservicefor"),
    @XmlEnumValue("isrealisationof")
    ISREALISATIONOF("isrealisationof"),
    @XmlEnumValue("isapproachfor")
    ISAPPROACHFOR("isapproachfor"),
    @XmlEnumValue("isplannedfor")
    ISPLANNEDFOR("isplannedfor"),
    @XmlEnumValue("isattestationof")
    ISATTESTATIONOF("isattestationof");
    private final String value;

    CDLNKvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDLNKvalues fromValue(String v) {
        for (CDLNKvalues c: CDLNKvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
