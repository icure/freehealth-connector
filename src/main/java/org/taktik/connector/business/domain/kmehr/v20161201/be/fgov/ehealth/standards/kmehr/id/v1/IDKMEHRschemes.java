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
 * <p>Classe Java pour ID-KMEHRschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ID-KMEHRschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ID-KMEHR"/>
 *     &lt;enumeration value="ID-IBAN"/>
 *     &lt;enumeration value="ID-SERIALNO"/>
 *     &lt;enumeration value="LOCAL"/>
 *     &lt;enumeration value="ID-CBE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "ID-KMEHRschemes")
@XmlEnum
public enum IDKMEHRschemes {

    @XmlEnumValue("ID-KMEHR")
    ID_KMEHR("ID-KMEHR"),
    @XmlEnumValue("ID-IBAN")
    ID_IBAN("ID-IBAN"),
    @XmlEnumValue("ID-SERIALNO")
    ID_SERIALNO("ID-SERIALNO"),
    LOCAL("LOCAL"),
    @XmlEnumValue("ID-CBE")
    ID_CBE("ID-CBE");
    private final String value;

    IDKMEHRschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IDKMEHRschemes fromValue(String v) {
        for (IDKMEHRschemes c: IDKMEHRschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
