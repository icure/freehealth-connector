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
 * <p>Classe Java pour CD-TUCO-STEMITYPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TUCO-STEMITYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="stemipci"/>
 *     &lt;enumeration value="stemirescue"/>
 *     &lt;enumeration value="stemilate"/>
 *     &lt;enumeration value="nonstemiurgent"/>
 *     &lt;enumeration value="nonstemielective"/>
 *     &lt;enumeration value="nonstemilate"/>
 *     &lt;enumeration value="emergentpci"/>
 *     &lt;enumeration value="electivepci"/>
 *     &lt;enumeration value="outofhospitalarrest"/>
 *     &lt;enumeration value="stagedpci"/>
 *     &lt;enumeration value="complicationpriorpci"/>
 *     &lt;enumeration value="recurrendischaemia"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TUCO-STEMITYPEvalues")
@XmlEnum
public enum CDTUCOSTEMITYPEvalues {

    @XmlEnumValue("stemipci")
    STEMIPCI("stemipci"),
    @XmlEnumValue("stemirescue")
    STEMIRESCUE("stemirescue"),
    @XmlEnumValue("stemilate")
    STEMILATE("stemilate"),
    @XmlEnumValue("nonstemiurgent")
    NONSTEMIURGENT("nonstemiurgent"),
    @XmlEnumValue("nonstemielective")
    NONSTEMIELECTIVE("nonstemielective"),
    @XmlEnumValue("nonstemilate")
    NONSTEMILATE("nonstemilate"),
    @XmlEnumValue("emergentpci")
    EMERGENTPCI("emergentpci"),
    @XmlEnumValue("electivepci")
    ELECTIVEPCI("electivepci"),
    @XmlEnumValue("outofhospitalarrest")
    OUTOFHOSPITALARREST("outofhospitalarrest"),
    @XmlEnumValue("stagedpci")
    STAGEDPCI("stagedpci"),
    @XmlEnumValue("complicationpriorpci")
    COMPLICATIONPRIORPCI("complicationpriorpci"),
    @XmlEnumValue("recurrendischaemia")
    RECURRENDISCHAEMIA("recurrendischaemia");
    private final String value;

    CDTUCOSTEMITYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTUCOSTEMITYPEvalues fromValue(String v) {
        for (CDTUCOSTEMITYPEvalues c: CDTUCOSTEMITYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
