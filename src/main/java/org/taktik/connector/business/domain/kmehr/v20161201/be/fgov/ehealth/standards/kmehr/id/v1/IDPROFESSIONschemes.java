//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ID-PROFESSIONschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ID-PROFESSIONschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ID-MEDEX"/>
 *     &lt;enumeration value="ID-CBE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "ID-PROFESSIONschemes")
@XmlEnum
public enum IDPROFESSIONschemes {

    @XmlEnumValue("ID-MEDEX")
    ID_MEDEX("ID-MEDEX"),
    @XmlEnumValue("ID-CBE")
    ID_CBE("ID-CBE");
    private final String value;

    IDPROFESSIONschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IDPROFESSIONschemes fromValue(String v) {
        for (IDPROFESSIONschemes c: IDPROFESSIONschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
