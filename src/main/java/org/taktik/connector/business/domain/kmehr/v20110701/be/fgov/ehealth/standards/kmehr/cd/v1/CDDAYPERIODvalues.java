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
 * <p>Classe Java pour CD-DAYPERIODvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-DAYPERIODvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="afterbreakfast"/>
 *     &lt;enumeration value="afterdinner"/>
 *     &lt;enumeration value="afterlunch"/>
 *     &lt;enumeration value="aftermeal"/>
 *     &lt;enumeration value="afternoon"/>
 *     &lt;enumeration value="beforebreakfast"/>
 *     &lt;enumeration value="beforedinner"/>
 *     &lt;enumeration value="beforelunch"/>
 *     &lt;enumeration value="betweenbreakfastandlunch"/>
 *     &lt;enumeration value="betweendinnerandsleep"/>
 *     &lt;enumeration value="betweenlunchanddinner"/>
 *     &lt;enumeration value="betweenmeals"/>
 *     &lt;enumeration value="evening"/>
 *     &lt;enumeration value="morning"/>
 *     &lt;enumeration value="night"/>
 *     &lt;enumeration value="thehourofsleep"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-DAYPERIODvalues")
@XmlEnum
public enum CDDAYPERIODvalues {

    @XmlEnumValue("afterbreakfast")
    AFTERBREAKFAST("afterbreakfast"),
    @XmlEnumValue("afterdinner")
    AFTERDINNER("afterdinner"),
    @XmlEnumValue("afterlunch")
    AFTERLUNCH("afterlunch"),
    @XmlEnumValue("aftermeal")
    AFTERMEAL("aftermeal"),
    @XmlEnumValue("afternoon")
    AFTERNOON("afternoon"),
    @XmlEnumValue("beforebreakfast")
    BEFOREBREAKFAST("beforebreakfast"),
    @XmlEnumValue("beforedinner")
    BEFOREDINNER("beforedinner"),
    @XmlEnumValue("beforelunch")
    BEFORELUNCH("beforelunch"),
    @XmlEnumValue("betweenbreakfastandlunch")
    BETWEENBREAKFASTANDLUNCH("betweenbreakfastandlunch"),
    @XmlEnumValue("betweendinnerandsleep")
    BETWEENDINNERANDSLEEP("betweendinnerandsleep"),
    @XmlEnumValue("betweenlunchanddinner")
    BETWEENLUNCHANDDINNER("betweenlunchanddinner"),
    @XmlEnumValue("betweenmeals")
    BETWEENMEALS("betweenmeals"),
    @XmlEnumValue("evening")
    EVENING("evening"),
    @XmlEnumValue("morning")
    MORNING("morning"),
    @XmlEnumValue("night")
    NIGHT("night"),
    @XmlEnumValue("thehourofsleep")
    THEHOUROFSLEEP("thehourofsleep");
    private final String value;

    CDDAYPERIODvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDDAYPERIODvalues fromValue(String v) {
        for (CDDAYPERIODvalues c: CDDAYPERIODvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
