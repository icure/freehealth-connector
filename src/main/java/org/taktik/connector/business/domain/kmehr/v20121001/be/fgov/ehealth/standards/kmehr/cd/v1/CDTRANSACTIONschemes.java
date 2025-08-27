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
 * <p>Classe Java pour CD-TRANSACTIONschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TRANSACTIONschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-TRANSACTION"/>
 *     &lt;enumeration value="CD-TRANSACTION-CARENET"/>
 *     &lt;enumeration value="CD-TRANSACTION-MAA"/>
 *     &lt;enumeration value="CD-CHAPTER4APPENDIX"/>
 *     &lt;enumeration value="CD-TRANSACTION-REG"/>
 *     &lt;enumeration value="CD-TRANSACTION-MYCARENET"/>
 *     &lt;enumeration value="LOCAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TRANSACTIONschemes")
@XmlEnum
public enum CDTRANSACTIONschemes {

    @XmlEnumValue("CD-TRANSACTION")
    CD_TRANSACTION("CD-TRANSACTION", "1.4"),
    @XmlEnumValue("CD-TRANSACTION-CARENET")
    CD_TRANSACTION_CARENET("CD-TRANSACTION-CARENET", "1.0"),
    @XmlEnumValue("CD-TRANSACTION-MAA")
    CD_TRANSACTION_MAA("CD-TRANSACTION-MAA", "1.0"),
    @XmlEnumValue("CD-CHAPTER4APPENDIX")
    CD_CHAPTER_4_APPENDIX("CD-CHAPTER4APPENDIX", "1.0"),
    @XmlEnumValue("CD-TRANSACTION-REG")
    CD_TRANSACTION_REG("CD-TRANSACTION-REG", "1.0"),
    @XmlEnumValue("CD-TRANSACTION-MYCARENET")
    CD_TRANSACTION_MYCARENET("CD-TRANSACTION-MYCARENET", "1.0"),
    LOCAL("LOCAL", "1.0");
    private final String value; //
    private final String version;
    CDTRANSACTIONschemes(String v, String vs) {
        value = v;
        version = vs;
    }

    public String value() {
        return value;
    } //

    public String version() {
        return version;
    }

    public static CDTRANSACTIONschemes fromValue(String v) {
        for (CDTRANSACTIONschemes c: CDTRANSACTIONschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
