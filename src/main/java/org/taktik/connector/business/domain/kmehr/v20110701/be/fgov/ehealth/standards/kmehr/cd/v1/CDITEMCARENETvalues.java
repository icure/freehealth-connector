//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:47:59 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-ITEM-CARENETvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ITEM-CARENETvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="accidenttype"/>
 *     &lt;enumeration value="advisingphysician"/>
 *     &lt;enumeration value="agreement"/>
 *     &lt;enumeration value="authorisedextensionenddatetime"/>
 *     &lt;enumeration value="begindatetime"/>
 *     &lt;enumeration value="billingdestinationnumber"/>
 *     &lt;enumeration value="insurancydetails"/>
 *     &lt;enumeration value="insurancystatus"/>
 *     &lt;enumeration value="messagenumber"/>
 *     &lt;enumeration value="messagetype"/>
 *     &lt;enumeration value="missingdocument"/>
 *     &lt;enumeration value="mutationbegindatetime"/>
 *     &lt;enumeration value="mutationdestination"/>
 *     &lt;enumeration value="nationalinsurance"/>
 *     &lt;enumeration value="otheradmission"/>
 *     &lt;enumeration value="protectionmeasure"/>
 *     &lt;enumeration value="refusalreason"/>
 *     &lt;enumeration value="requestedextensiondatetime"/>
 *     &lt;enumeration value="requestedextensionenddatetime"/>
 *     &lt;enumeration value="siscardadjustment"/>
 *     &lt;enumeration value="siscarderror"/>
 *     &lt;enumeration value="socialcategory"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ITEM-CARENETvalues")
@XmlEnum
public enum CDITEMCARENETvalues {

    @XmlEnumValue("accidenttype")
    ACCIDENTTYPE("accidenttype"),
    @XmlEnumValue("advisingphysician")
    ADVISINGPHYSICIAN("advisingphysician"),
    @XmlEnumValue("agreement")
    AGREEMENT("agreement"),
    @XmlEnumValue("authorisedextensionenddatetime")
    AUTHORISEDEXTENSIONENDDATETIME("authorisedextensionenddatetime"),
    @XmlEnumValue("begindatetime")
    BEGINDATETIME("begindatetime"),
    @XmlEnumValue("billingdestinationnumber")
    BILLINGDESTINATIONNUMBER("billingdestinationnumber"),
    @XmlEnumValue("insurancydetails")
    INSURANCYDETAILS("insurancydetails"),
    @XmlEnumValue("insurancystatus")
    INSURANCYSTATUS("insurancystatus"),
    @XmlEnumValue("messagenumber")
    MESSAGENUMBER("messagenumber"),
    @XmlEnumValue("messagetype")
    MESSAGETYPE("messagetype"),
    @XmlEnumValue("missingdocument")
    MISSINGDOCUMENT("missingdocument"),
    @XmlEnumValue("mutationbegindatetime")
    MUTATIONBEGINDATETIME("mutationbegindatetime"),
    @XmlEnumValue("mutationdestination")
    MUTATIONDESTINATION("mutationdestination"),
    @XmlEnumValue("nationalinsurance")
    NATIONALINSURANCE("nationalinsurance"),
    @XmlEnumValue("otheradmission")
    OTHERADMISSION("otheradmission"),
    @XmlEnumValue("protectionmeasure")
    PROTECTIONMEASURE("protectionmeasure"),
    @XmlEnumValue("refusalreason")
    REFUSALREASON("refusalreason"),
    @XmlEnumValue("requestedextensiondatetime")
    REQUESTEDEXTENSIONDATETIME("requestedextensiondatetime"),
    @XmlEnumValue("requestedextensionenddatetime")
    REQUESTEDEXTENSIONENDDATETIME("requestedextensionenddatetime"),
    @XmlEnumValue("siscardadjustment")
    SISCARDADJUSTMENT("siscardadjustment"),
    @XmlEnumValue("siscarderror")
    SISCARDERROR("siscarderror"),
    @XmlEnumValue("socialcategory")
    SOCIALCATEGORY("socialcategory");
    private final String value;

    CDITEMCARENETvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDITEMCARENETvalues fromValue(String v) {
        for (CDITEMCARENETvalues c: CDITEMCARENETvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
