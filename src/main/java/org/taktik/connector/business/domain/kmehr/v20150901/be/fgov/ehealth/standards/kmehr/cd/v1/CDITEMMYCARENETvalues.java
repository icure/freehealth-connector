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
 * <p>Classe Java pour CD-ITEM-MYCARENETvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ITEM-MYCARENETvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="fee"/>
 *     &lt;enumeration value="financialcontract"/>
 *     &lt;enumeration value="patientfee"/>
 *     &lt;enumeration value="payment"/>
 *     &lt;enumeration value="reimbursement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ITEM-MYCARENETvalues")
@XmlEnum
public enum CDITEMMYCARENETvalues {

    @XmlEnumValue("fee")
    FEE("fee"),
    @XmlEnumValue("financialcontract")
    FINANCIALCONTRACT("financialcontract"),
    @XmlEnumValue("patientfee")
    PATIENTFEE("patientfee"),
    @XmlEnumValue("payment")
    PAYMENT("payment"),
    @XmlEnumValue("reimbursement")
    REIMBURSEMENT("reimbursement");
    private final String value;

    CDITEMMYCARENETvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDITEMMYCARENETvalues fromValue(String v) {
        for (CDITEMMYCARENETvalues c: CDITEMMYCARENETvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
