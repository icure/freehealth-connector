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
 * <p>Classe Java pour CD-BCR-DIFFERENTATIONDEGREEvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-BCR-DIFFERENTATIONDEGREEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="welldifferentiated"/>
 *     &lt;enumeration value="moderatelydifferentiated"/>
 *     &lt;enumeration value="poorlydifferentiated"/>
 *     &lt;enumeration value="undifferentiatedanaplastic"/>
 *     &lt;enumeration value="tcell"/>
 *     &lt;enumeration value="bcell"/>
 *     &lt;enumeration value="nullcell"/>
 *     &lt;enumeration value="nkcell"/>
 *     &lt;enumeration value="unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-BCR-DIFFERENTATIONDEGREEvalues")
@XmlEnum
public enum CDBCRDIFFERENTATIONDEGREEvalues {

    @XmlEnumValue("welldifferentiated")
    WELLDIFFERENTIATED("welldifferentiated"),
    @XmlEnumValue("moderatelydifferentiated")
    MODERATELYDIFFERENTIATED("moderatelydifferentiated"),
    @XmlEnumValue("poorlydifferentiated")
    POORLYDIFFERENTIATED("poorlydifferentiated"),
    @XmlEnumValue("undifferentiatedanaplastic")
    UNDIFFERENTIATEDANAPLASTIC("undifferentiatedanaplastic"),
    @XmlEnumValue("tcell")
    TCELL("tcell"),
    @XmlEnumValue("bcell")
    BCELL("bcell"),
    @XmlEnumValue("nullcell")
    NULLCELL("nullcell"),
    @XmlEnumValue("nkcell")
    NKCELL("nkcell"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown");
    private final String value;

    CDBCRDIFFERENTATIONDEGREEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDBCRDIFFERENTATIONDEGREEvalues fromValue(String v) {
        for (CDBCRDIFFERENTATIONDEGREEvalues c: CDBCRDIFFERENTATIONDEGREEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
