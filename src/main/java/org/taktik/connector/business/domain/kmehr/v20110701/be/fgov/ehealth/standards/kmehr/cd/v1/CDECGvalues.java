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
 * <p>Classe Java pour CD-ECGvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ECGvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VR"/>
 *     &lt;enumeration value="AR"/>
 *     &lt;enumeration value="PR"/>
 *     &lt;enumeration value="QRS"/>
 *     &lt;enumeration value="QT"/>
 *     &lt;enumeration value="QTc"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="T"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ECGvalues")
@XmlEnum
public enum CDECGvalues {

    VR("VR"),
    AR("AR"),
    PR("PR"),
    QRS("QRS"),
    QT("QT"),
    @XmlEnumValue("QTc")
    Q_TC("QTc"),
    P("P"),
    R("R"),
    T("T");
    private final String value;

    CDECGvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDECGvalues fromValue(String v) {
        for (CDECGvalues c: CDECGvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
