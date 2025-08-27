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
 * <p>Classe Java pour CD-EBIRTH-CAESAREANINDICATIONvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-EBIRTH-CAESAREANINDICATIONvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="previouscaesareansection"/>
 *     &lt;enumeration value="breechpresentation"/>
 *     &lt;enumeration value="transversepresentation"/>
 *     &lt;enumeration value="foetaldistress"/>
 *     &lt;enumeration value="dystocienotinlabour"/>
 *     &lt;enumeration value="dystocieinlabourinsufficientdilatation"/>
 *     &lt;enumeration value="dystocieinlabourinsufficientexpulsion"/>
 *     &lt;enumeration value="maternalindication"/>
 *     &lt;enumeration value="abruptioplacentae"/>
 *     &lt;enumeration value="requestedbypatient"/>
 *     &lt;enumeration value="multiplepregnancy"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-EBIRTH-CAESAREANINDICATIONvalues")
@XmlEnum
public enum CDEBIRTHCAESAREANINDICATIONvalues {

    @XmlEnumValue("previouscaesareansection")
    PREVIOUSCAESAREANSECTION("previouscaesareansection"),
    @XmlEnumValue("breechpresentation")
    BREECHPRESENTATION("breechpresentation"),
    @XmlEnumValue("transversepresentation")
    TRANSVERSEPRESENTATION("transversepresentation"),
    @XmlEnumValue("foetaldistress")
    FOETALDISTRESS("foetaldistress"),
    @XmlEnumValue("dystocienotinlabour")
    DYSTOCIENOTINLABOUR("dystocienotinlabour"),
    @XmlEnumValue("dystocieinlabourinsufficientdilatation")
    DYSTOCIEINLABOURINSUFFICIENTDILATATION("dystocieinlabourinsufficientdilatation"),
    @XmlEnumValue("dystocieinlabourinsufficientexpulsion")
    DYSTOCIEINLABOURINSUFFICIENTEXPULSION("dystocieinlabourinsufficientexpulsion"),
    @XmlEnumValue("maternalindication")
    MATERNALINDICATION("maternalindication"),
    @XmlEnumValue("abruptioplacentae")
    ABRUPTIOPLACENTAE("abruptioplacentae"),
    @XmlEnumValue("requestedbypatient")
    REQUESTEDBYPATIENT("requestedbypatient"),
    @XmlEnumValue("multiplepregnancy")
    MULTIPLEPREGNANCY("multiplepregnancy"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    CDEBIRTHCAESAREANINDICATIONvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDEBIRTHCAESAREANINDICATIONvalues fromValue(String v) {
        for (CDEBIRTHCAESAREANINDICATIONvalues c: CDEBIRTHCAESAREANINDICATIONvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
