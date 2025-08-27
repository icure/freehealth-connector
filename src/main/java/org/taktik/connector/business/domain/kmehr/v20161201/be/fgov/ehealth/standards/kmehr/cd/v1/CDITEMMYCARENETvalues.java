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
 *     &lt;enumeration value="refusal"/>
 *     &lt;enumeration value="patientpaid"/>
 *     &lt;enumeration value="supplement"/>
 *     &lt;enumeration value="paymentreceivingparty"/>
 *     &lt;enumeration value="internship"/>
 *     &lt;enumeration value="documentidentity"/>
 *     &lt;enumeration value="invoicingnumber"/>
 *     &lt;enumeration value="reimbursement-fpssi"/>
 *     &lt;enumeration value="reimbursement-pswc"/>
 *     &lt;enumeration value="umc"/>
 *     &lt;enumeration value="mediprimanumber"/>
 *     &lt;enumeration value="pswc"/>
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
    REIMBURSEMENT("reimbursement"),
    @XmlEnumValue("refusal")
    REFUSAL("refusal"),
    @XmlEnumValue("patientpaid")
    PATIENTPAID("patientpaid"),
    @XmlEnumValue("supplement")
    SUPPLEMENT("supplement"),
    @XmlEnumValue("paymentreceivingparty")
    PAYMENTRECEIVINGPARTY("paymentreceivingparty"),
    @XmlEnumValue("internship")
    INTERNSHIP("internship"),
    @XmlEnumValue("documentidentity")
    DOCUMENTIDENTITY("documentidentity"),
    @XmlEnumValue("invoicingnumber")
    INVOICINGNUMBER("invoicingnumber"),
    @XmlEnumValue("reimbursement-fpssi")
    REIMBURSEMENT_FPSSI("reimbursement-fpssi"),
    @XmlEnumValue("reimbursement-pswc")
    REIMBURSEMENT_PSWC("reimbursement-pswc"),
    @XmlEnumValue("umc")
    UMC("umc"),
    @XmlEnumValue("mediprimanumber")
    MEDIPRIMANUMBER("mediprimanumber"),
    @XmlEnumValue("pswc")
    PSWC("pswc");
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
