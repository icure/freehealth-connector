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
 * <p>Classe Java pour CD-TRANSACTION-TYPEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TRANSACTION-TYPEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="incapacity"/>
 *     &lt;enumeration value="incapacityextension"/>
 *     &lt;enumeration value="incapacityrelapse"/>
 *     &lt;enumeration value="nursing"/>
 *     &lt;enumeration value="physiotherapy"/>
 *     &lt;enumeration value="intermediarynursing"/>
 *     &lt;enumeration value="intermediaryphysiotherapy"/>
 *     &lt;enumeration value="transferdocument"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TRANSACTION-TYPEvalues")
@XmlEnum
public enum CDTRANSACTIONTYPEvalues {

    @XmlEnumValue("incapacity")
    INCAPACITY("incapacity"),
    @XmlEnumValue("incapacityextension")
    INCAPACITYEXTENSION("incapacityextension"),
    @XmlEnumValue("incapacityrelapse")
    INCAPACITYRELAPSE("incapacityrelapse"),
    @XmlEnumValue("nursing")
    NURSING("nursing"),
    @XmlEnumValue("physiotherapy")
    PHYSIOTHERAPY("physiotherapy"),
    @XmlEnumValue("intermediarynursing")
    INTERMEDIARYNURSING("intermediarynursing"),
    @XmlEnumValue("intermediaryphysiotherapy")
    INTERMEDIARYPHYSIOTHERAPY("intermediaryphysiotherapy"),
    @XmlEnumValue("transferdocument")
    TRANSFERDOCUMENT("transferdocument");
    private final String value;

    CDTRANSACTIONTYPEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTRANSACTIONTYPEvalues fromValue(String v) {
        for (CDTRANSACTIONTYPEvalues c: CDTRANSACTIONTYPEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
