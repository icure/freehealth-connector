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
 * <p>Classe Java pour CD-MAA-REQUESTTYPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-MAA-REQUESTTYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="newrequest"/>
 *     &lt;enumeration value="extension"/>
 *     &lt;enumeration value="noncontinuousextension"/>
 *     &lt;enumeration value="complimentaryannex"/>
 *     &lt;enumeration value="cancellation"/>
 *     &lt;enumeration value="closure"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-MAA-REQUESTTYPEvalues")
@XmlEnum
public enum CDMAAREQUESTTYPEvalues {

    @XmlEnumValue("newrequest")
    NEWREQUEST("newrequest"),
    @XmlEnumValue("extension")
    EXTENSION("extension"),
    @XmlEnumValue("noncontinuousextension")
    NONCONTINUOUSEXTENSION("noncontinuousextension"),
    @XmlEnumValue("complimentaryannex")
    COMPLIMENTARYANNEX("complimentaryannex"),
    @XmlEnumValue("cancellation")
    CANCELLATION("cancellation"),
    @XmlEnumValue("closure")
    CLOSURE("closure");
    private final String value;

    CDMAAREQUESTTYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDMAAREQUESTTYPEvalues fromValue(String v) {
        for (CDMAAREQUESTTYPEvalues c: CDMAAREQUESTTYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
