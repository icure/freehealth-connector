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
 * <p>Classe Java pour CD-STENT-SEGMENTvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-STENT-SEGMENTvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="proxrca"/>
 *     &lt;enumeration value="midrca"/>
 *     &lt;enumeration value="distrca"/>
 *     &lt;enumeration value="rightposteriordescending"/>
 *     &lt;enumeration value="leftmain"/>
 *     &lt;enumeration value="proxlad"/>
 *     &lt;enumeration value="midlad"/>
 *     &lt;enumeration value="distlad"/>
 *     &lt;enumeration value="d1"/>
 *     &lt;enumeration value="d2"/>
 *     &lt;enumeration value="d3"/>
 *     &lt;enumeration value="proxcx"/>
 *     &lt;enumeration value="intermediatebissectrice"/>
 *     &lt;enumeration value="m1"/>
 *     &lt;enumeration value="m2"/>
 *     &lt;enumeration value="distcx"/>
 *     &lt;enumeration value="pl1"/>
 *     &lt;enumeration value="pl2"/>
 *     &lt;enumeration value="pl3"/>
 *     &lt;enumeration value="leftposteriordescending"/>
 *     &lt;enumeration value="rv"/>
 *     &lt;enumeration value="rightpl2"/>
 *     &lt;enumeration value="rightpl3"/>
 *     &lt;enumeration value="pl4"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-STENT-SEGMENTvalues")
@XmlEnum
public enum CDSTENTSEGMENTvalues {

    @XmlEnumValue("proxrca")
    PROXRCA("proxrca"),
    @XmlEnumValue("midrca")
    MIDRCA("midrca"),
    @XmlEnumValue("distrca")
    DISTRCA("distrca"),
    @XmlEnumValue("rightposteriordescending")
    RIGHTPOSTERIORDESCENDING("rightposteriordescending"),
    @XmlEnumValue("leftmain")
    LEFTMAIN("leftmain"),
    @XmlEnumValue("proxlad")
    PROXLAD("proxlad"),
    @XmlEnumValue("midlad")
    MIDLAD("midlad"),
    @XmlEnumValue("distlad")
    DISTLAD("distlad"),
    @XmlEnumValue("d1")
    D_1("d1"),
    @XmlEnumValue("d2")
    D_2("d2"),
    @XmlEnumValue("d3")
    D_3("d3"),
    @XmlEnumValue("proxcx")
    PROXCX("proxcx"),
    @XmlEnumValue("intermediatebissectrice")
    INTERMEDIATEBISSECTRICE("intermediatebissectrice"),
    @XmlEnumValue("m1")
    M_1("m1"),
    @XmlEnumValue("m2")
    M_2("m2"),
    @XmlEnumValue("distcx")
    DISTCX("distcx"),
    @XmlEnumValue("pl1")
    PL_1("pl1"),
    @XmlEnumValue("pl2")
    PL_2("pl2"),
    @XmlEnumValue("pl3")
    PL_3("pl3"),
    @XmlEnumValue("leftposteriordescending")
    LEFTPOSTERIORDESCENDING("leftposteriordescending"),
    @XmlEnumValue("rv")
    RV("rv"),
    @XmlEnumValue("rightpl2")
    RIGHTPL_2("rightpl2"),
    @XmlEnumValue("rightpl3")
    RIGHTPL_3("rightpl3"),
    @XmlEnumValue("pl4")
    PL_4("pl4");
    private final String value;

    CDSTENTSEGMENTvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSTENTSEGMENTvalues fromValue(String v) {
        for (CDSTENTSEGMENTvalues c: CDSTENTSEGMENTvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
