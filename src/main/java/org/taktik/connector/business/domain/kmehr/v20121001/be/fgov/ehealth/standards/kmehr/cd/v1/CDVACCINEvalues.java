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
 * <p>Classe Java pour CD-VACCINEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-VACCINEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="polio"/>
 *     &lt;enumeration value="diteper"/>
 *     &lt;enumeration value="haemo"/>
 *     &lt;enumeration value="mmr"/>
 *     &lt;enumeration value="hepatitiesb"/>
 *     &lt;enumeration value="mmr12"/>
 *     &lt;enumeration value="dite12"/>
 *     &lt;enumeration value="meningitisc"/>
 *     &lt;enumeration value="influenza"/>
 *     &lt;enumeration value="pneumonia"/>
 *     &lt;enumeration value="ditepro"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-VACCINEvalues")
@XmlEnum
public enum CDVACCINEvalues {

    @XmlEnumValue("polio")
    POLIO("polio"),
    @XmlEnumValue("diteper")
    DITEPER("diteper"),
    @XmlEnumValue("haemo")
    HAEMO("haemo"),
    @XmlEnumValue("mmr")
    MMR("mmr"),
    @XmlEnumValue("hepatitiesb")
    HEPATITIESB("hepatitiesb"),
    @XmlEnumValue("mmr12")
    MMR_12("mmr12"),
    @XmlEnumValue("dite12")
    DITE_12("dite12"),
    @XmlEnumValue("meningitisc")
    MENINGITISC("meningitisc"),
    @XmlEnumValue("influenza")
    INFLUENZA("influenza"),
    @XmlEnumValue("pneumonia")
    PNEUMONIA("pneumonia"),
    @XmlEnumValue("ditepro")
    DITEPRO("ditepro");
    private final String value;

    CDVACCINEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDVACCINEvalues fromValue(String v) {
        for (CDVACCINEvalues c: CDVACCINEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
