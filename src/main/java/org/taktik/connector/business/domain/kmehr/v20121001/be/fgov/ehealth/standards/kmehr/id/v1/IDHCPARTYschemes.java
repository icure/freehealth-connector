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
 * <p>Classe Java pour ID-HCPARTYschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ID-HCPARTYschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ID-HCPARTY"/>
 *     &lt;enumeration value="INSS"/>
 *     &lt;enumeration value="LOCAL"/>
 *     &lt;enumeration value="ID-ENCRYPTION-APPLICATION"/>
 *     &lt;enumeration value="ID-ENCRYPTION-ACTOR"/>
 *     &lt;enumeration value="ID-INSURANCE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "ID-HCPARTYschemes")
@XmlEnum
public enum IDHCPARTYschemes {

    @XmlEnumValue("ID-HCPARTY")
    ID_HCPARTY("ID-HCPARTY"),
    INSS("INSS"),
    LOCAL("LOCAL"),
    @XmlEnumValue("ID-ENCRYPTION-APPLICATION")
    ID_ENCRYPTION_APPLICATION("ID-ENCRYPTION-APPLICATION"),
    @XmlEnumValue("ID-ENCRYPTION-ACTOR")
    ID_ENCRYPTION_ACTOR("ID-ENCRYPTION-ACTOR"),
    @XmlEnumValue("ID-INSURANCE")
    ID_INSURANCE("ID-INSURANCE");
    private final String value;

    IDHCPARTYschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IDHCPARTYschemes fromValue(String v) {
        for (IDHCPARTYschemes c: IDHCPARTYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
