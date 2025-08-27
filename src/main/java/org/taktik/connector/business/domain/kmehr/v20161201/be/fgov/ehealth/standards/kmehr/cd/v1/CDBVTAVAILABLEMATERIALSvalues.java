//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-BVT-AVAILABLEMATERIALSvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-BVT-AVAILABLEMATERIALSvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="cytology"/>
 *     &lt;enumeration value="dna"/>
 *     &lt;enumeration value="rna"/>
 *     &lt;enumeration value="proteins"/>
 *     &lt;enumeration value="correspondingnormaltissue"/>
 *     &lt;enumeration value="tissue"/>
 *     &lt;enumeration value="serum"/>
 *     &lt;enumeration value="plasma"/>
 *     &lt;enumeration value="blood"/>
 *     &lt;enumeration value="urine"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-BVT-AVAILABLEMATERIALSvalues")
@XmlEnum
public enum CDBVTAVAILABLEMATERIALSvalues {

    @XmlEnumValue("cytology")
    CYTOLOGY("cytology"),
    @XmlEnumValue("dna")
    DNA("dna"),
    @XmlEnumValue("rna")
    RNA("rna"),
    @XmlEnumValue("proteins")
    PROTEINS("proteins"),
    @XmlEnumValue("correspondingnormaltissue")
    CORRESPONDINGNORMALTISSUE("correspondingnormaltissue"),
    @XmlEnumValue("tissue")
    TISSUE("tissue"),
    @XmlEnumValue("serum")
    SERUM("serum"),
    @XmlEnumValue("plasma")
    PLASMA("plasma"),
    @XmlEnumValue("blood")
    BLOOD("blood"),
    @XmlEnumValue("urine")
    URINE("urine"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    CDBVTAVAILABLEMATERIALSvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDBVTAVAILABLEMATERIALSvalues fromValue(String v) {
        for (CDBVTAVAILABLEMATERIALSvalues c: CDBVTAVAILABLEMATERIALSvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
