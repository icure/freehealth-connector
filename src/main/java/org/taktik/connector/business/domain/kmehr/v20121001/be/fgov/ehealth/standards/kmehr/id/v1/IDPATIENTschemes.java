//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:06 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.id.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ID-PATIENTschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ID-PATIENTschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ID-PATIENT"/>
 *     &lt;enumeration value="INSS"/>
 *     &lt;enumeration value="EID-CARDNO"/>
 *     &lt;enumeration value="SIS-CARDNO"/>
 *     &lt;enumeration value="LOCAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "ID-PATIENTschemes")
@XmlEnum
public enum IDPATIENTschemes {

    @XmlEnumValue("ID-PATIENT")
    ID_PATIENT("ID-PATIENT"),
    INSS("INSS"),
    @XmlEnumValue("EID-CARDNO")
    EID_CARDNO("EID-CARDNO"),
    @XmlEnumValue("SIS-CARDNO")
    SIS_CARDNO("SIS-CARDNO"),
    LOCAL("LOCAL");
    private final String value;

    IDPATIENTschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IDPATIENTschemes fromValue(String v) {
        for (IDPATIENTschemes c: IDPATIENTschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
