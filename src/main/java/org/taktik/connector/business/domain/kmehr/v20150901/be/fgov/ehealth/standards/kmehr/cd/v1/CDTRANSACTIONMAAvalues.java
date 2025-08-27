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
 * <p>Classe Java pour CD-TRANSACTION-MAAvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TRANSACTION-MAAvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="agreementrequest"/>
 *     &lt;enumeration value="agreementresponse"/>
 *     &lt;enumeration value="freeappendix"/>
 *     &lt;enumeration value="reglementaryappendix"/>
 *     &lt;enumeration value="consultationrequest"/>
 *     &lt;enumeration value="consultationresponse"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TRANSACTION-MAAvalues")
@XmlEnum
public enum CDTRANSACTIONMAAvalues {

    @XmlEnumValue("agreementrequest")
    AGREEMENTREQUEST("agreementrequest"),
    @XmlEnumValue("agreementresponse")
    AGREEMENTRESPONSE("agreementresponse"),
    @XmlEnumValue("freeappendix")
    FREEAPPENDIX("freeappendix"),
    @XmlEnumValue("reglementaryappendix")
    REGLEMENTARYAPPENDIX("reglementaryappendix"),
    @XmlEnumValue("consultationrequest")
    CONSULTATIONREQUEST("consultationrequest"),
    @XmlEnumValue("consultationresponse")
    CONSULTATIONRESPONSE("consultationresponse");
    private final String value;

    CDTRANSACTIONMAAvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTRANSACTIONMAAvalues fromValue(String v) {
        for (CDTRANSACTIONMAAvalues c: CDTRANSACTIONMAAvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
