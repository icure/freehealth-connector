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
 * <p>Classe Java pour CD-ITEM-BVTvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ITEM-BVTvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="referenceid"/>
 *     &lt;enumeration value="patientopposition"/>
 *     &lt;enumeration value="sample"/>
 *     &lt;enumeration value="biopsynumber"/>
 *     &lt;enumeration value="technicalremarks"/>
 *     &lt;enumeration value="lab"/>
 *     &lt;enumeration value="error"/>
 *     &lt;enumeration value="status"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ITEM-BVTvalues")
@XmlEnum
public enum CDITEMBVTvalues {

    @XmlEnumValue("referenceid")
    REFERENCEID("referenceid"),
    @XmlEnumValue("patientopposition")
    PATIENTOPPOSITION("patientopposition"),
    @XmlEnumValue("sample")
    SAMPLE("sample"),
    @XmlEnumValue("biopsynumber")
    BIOPSYNUMBER("biopsynumber"),
    @XmlEnumValue("technicalremarks")
    TECHNICALREMARKS("technicalremarks"),
    @XmlEnumValue("lab")
    LAB("lab"),
    @XmlEnumValue("error")
    ERROR("error"),
    @XmlEnumValue("status")
    STATUS("status");
    private final String value;

    CDITEMBVTvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDITEMBVTvalues fromValue(String v) {
        for (CDITEMBVTvalues c: CDITEMBVTvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
